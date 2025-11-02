package com.riane.repository.repository

import android.app.Activity
import android.content.Context
import androidx.credentials.exceptions.domerrors.QuotaExceededError
import com.android.billingclient.api.AcknowledgePurchaseParams
import com.android.billingclient.api.AcknowledgePurchaseResponseListener
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.ConsumeParams
import com.android.billingclient.api.ProductDetails
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.PurchasesUpdatedListener
import com.android.billingclient.api.QueryProductDetailsParams
import com.android.billingclient.api.consumePurchase
import com.android.billingclient.api.queryProductDetails
import com.riane.remote.source.AuthRemoteDataSource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.resume

/**
 * https://developer.android.com/google/play/billing/integrate?hl=zh-cn
 * https://juejin.cn/post/7271991667245744165
 *https://juejin.cn/post/7110434269155033118
 */
class GooglePayRepositoryImpl @Inject constructor(
    @ApplicationContext private val appContext: Context,
) {

    /**
     * 回调里来获取用户的支付结果
     * 假如用户已支付成功，Purchase 就包含了此笔订单的具体信息，包括 ProductId、OrderId、Quantity、PurchaseTime 等
     */
    private val purchasesUpdateListener =
        PurchasesUpdatedListener { billingResult: BillingResult, purchases: List<Purchase>? ->

            when(billingResult.responseCode){
                BillingClient.BillingResponseCode.OK -> {
                    if (!purchases.isNullOrEmpty()){
                        purchases.forEach{
                            when(it.purchaseState){
                                Purchase.PurchaseState.PURCHASED -> {
                                    //用户支付成功
                                }

                                Purchase.PurchaseState.PENDING -> {
                                    //用户仅是预创建了订单，还未真正付款
                                }

                                Purchase.PurchaseState.UNSPECIFIED_STATE -> {
                                    //未知
                                }
                            }
                        }
                    }
                }
                BillingClient.BillingResponseCode.USER_CANCELED -> {

                }
                else -> {

                }
            }

        }

    private lateinit var mBillingClient: BillingClient

    /**
     * 与GooglePlay建立连接
     */
    suspend fun startConnection(context: Context) {
        mBillingClient = buildBillingClient(context = context, purchasesUpdateListener)
        startConnection(billingClient = mBillingClient)
    }

    /**
     * 初始化BillingClient
     */
    private fun buildBillingClient(
        context: Context,
        listener: PurchasesUpdatedListener
    ): BillingClient {
        return BillingClient.newBuilder(context).setListener(listener).enablePendingPurchases()
            .build()
    }

    /**
     * 与GooglePlay连接状态监听
     */
    private suspend fun startConnection(billingClient: BillingClient): BillingResult? {
        return withContext(context = Dispatchers.Default) {
            if (billingClient.isReady){
                return@withContext null
            }
            return@withContext suspendCancellableCoroutine { continuation ->
                mBillingClient.startConnection(object : BillingClientStateListener{
                    override fun onBillingServiceDisconnected() {
                        //处理与 Google Play 失去连接的问题。
                        if (!continuation.isCompleted) {
                            continuation.resume(value = null)
                        }
                    }

                    override fun onBillingSetupFinished(billingResult: BillingResult) {
                        if (!continuation.isCompleted){
                            continuation.resume( value = billingResult)
                        }
                    }

                })

            }
        }
    }

    /**
     * 商品类型也即 一次性商品 INAPP 和 订阅型商品 SUBS 两种
     */
    private suspend fun queryProductDetails(){
        //查询一次性商品
        queryProductDetails(
            billingClient = mBillingClient,
            productIdList = setOf("1", "2"),
            productType = BillingClient.ProductType.INAPP
        )
        //查询订阅型商品
        queryProductDetails(
            billingClient = mBillingClient,
            productIdList = setOf("1", "2"),
            productType = BillingClient.ProductType.SUBS
        )
    }

    /**
     * 不管是一次性商品还是订阅型商品，都通过 ProductDetails 来承载具体的商品信息
     *
     */
    private suspend fun queryProductDetails(
        billingClient: BillingClient,
        productIdList: Set<String>,
        productType: String) : List<ProductDetails>?{  //
        return withContext(context = Dispatchers.Default){
            if (!billingClient.isReady || productIdList.isEmpty()){
                return@withContext null
            }

            val productDetailParamsList = productIdList.map {
                QueryProductDetailsParams
                    .Product
                    .newBuilder()
                    .setProductId(it)
                    .setProductType(productType)
                    .build()
            }

            val queryProductDetailsParams = QueryProductDetailsParams
                .newBuilder()
                .setProductList(productDetailParamsList)
                .build()

            val productDetailResult = mBillingClient.queryProductDetails(queryProductDetailsParams)
            productDetailResult.productDetailsList
        }
    }

    /**
     * 假如要购买的是一次性商品，支付参数仅需要 ProductDetails 即可
     */
    private suspend fun launchBilling(
        activity: Activity,
        billingClient: BillingClient,
        productDetails: ProductDetails): BillingResult {
        return withContext(context = Dispatchers.Main.immediate) {
            val productDetailsParams = BillingFlowParams
                .ProductDetailsParams
                .newBuilder()
                .setProductDetails(productDetails)
                .build()

            val billingFlowParams = BillingFlowParams
                .newBuilder()
                .setProductDetailsParamsList(listOf(productDetailsParams))
                .build()
            billingClient.launchBillingFlow(activity, billingFlowParams)
        }
    }

    /**
     *假如要购买的是订阅型商品，则需要同时传递 ProductDetails 和 offerToken
     *
     */
    private suspend fun launchBilling(
        activity: Activity,
        billingClient: BillingClient,
        productDetails: ProductDetails,
        offerToken: String
    ): BillingResult {
        return withContext(context = Dispatchers.Main.immediate) {
            val productDetailsParams = BillingFlowParams
                .ProductDetailsParams
                .newBuilder()
                .setProductDetails(productDetails)
                .setOfferToken(offerToken)
                .build()
            val billingFlowParams = BillingFlowParams
                .newBuilder()
                .setProductDetailsParamsList(listOf(productDetailsParams))
                .build()
            billingClient.launchBillingFlow(activity, billingFlowParams)
        }
    }

    /**
     * 用户支付成功后，就需要对订单进行确认了，否则 Google Play 会在限定时间内退款给用户
     */
    private suspend fun acknowledgePurchase(
        billingClient: BillingClient,
        purchase: Purchase
    ) = suspendCancellableCoroutine{ coroutine ->
        if (purchase.purchaseState != Purchase.PurchaseState.PURCHASED) {
            coroutine.resume(false)
        }
        if (purchase.isAcknowledged) {
            coroutine.resume(true)
        }
        if (!billingClient.isReady) {
            coroutine.resume(false)
        }
        val acknowledgePurchaseParams = AcknowledgePurchaseParams
            .newBuilder()
            .setPurchaseToken(purchase.purchaseToken)
            .build()
        var tempResult: Boolean = false
        val acknowledgePurchase = billingClient.acknowledgePurchase(acknowledgePurchaseParams
        ) { result ->
            coroutine.resume(result.responseCode == BillingClient.BillingResponseCode.OK)
        }
    }

    /**
     * 如果用户购买的是消耗型的一次性商品，那么就需要根据实际业务择机对订单执行消耗操作
     */
    private suspend fun consumePurchase(
        billingClient: BillingClient,
        purchase: Purchase
    ): Boolean {
        return withContext(context = Dispatchers.Default) {
            if (purchase.purchaseState != Purchase.PurchaseState.PURCHASED) {
                return@withContext false
            }
            if (!billingClient.isReady) {
                return@withContext false
            }
            val consumeParams = ConsumeParams
                .newBuilder()
                .setPurchaseToken(purchase.purchaseToken)
                .build()
            val consumeResult = billingClient.consumePurchase(consumeParams)
            consumeResult.billingResult.responseCode == BillingClient.BillingResponseCode.OK
        }
    }

}