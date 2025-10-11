package com.riane.ui.widget

import android.graphics.Typeface
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableStateSetOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.drawText
import androidx.compose.ui.unit.TextUnit

/**
 * https://juejin.cn/post/6996568363581308959
 */
@Composable
fun WaveLoading(
    modifier: Modifier,
    text: String,
    textSize: TextUnit,
    waveColor: Color,
    downTextColor: Color = Color.White,
    //贝塞尔动画
    animationSpec: InfiniteRepeatableSpec<Float> = infiniteRepeatable(
        animation = tween(
            durationMillis = 500, easing = CubicBezierEasing(0.2f, 0.2f, 0.7f, 0.9f)
        ), repeatMode = RepeatMode.Restart
    )
) {
    //获得约束大小
    BoxWithConstraints(modifier = modifier) {
        val density = LocalDensity.current.density
        val circleSizeDp = minOf(maxWidth, maxHeight)
        val circleSizePx = circleSizeDp.value * density
        val waveWidth = circleSizePx / 1.2f
        val waveHeight = circleSizePx / 10f
        //画笔
        val textPaint by remember {
            mutableStateOf(Paint().asFrameworkPaint().apply {
                isAntiAlias = true
                isDither = true
                typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)
                textAlign = android.graphics.Paint.Align.CENTER
            })
        }
        //波浪纹路径
        val wavePath by remember { mutableStateOf(Path()) }
        //圆形路径
        val circlePath by remember {
            mutableStateOf(Path().apply {
                addArc(
                    Rect(0f, 0f, circleSizePx, circleSizePx), 0f, 360f
                )
            })
        }

        //创建一个无限过渡动画（InfiniteTransition）实例，并记住它
        val animateValue by rememberInfiniteTransition().animateFloat(
            initialValue = 0f, targetValue = waveWidth, animationSpec = animationSpec
        )


        Canvas(modifier.requiredSize(size = circleSizeDp)) {

            drawTextToCenter(
                text = text, textPaint = textPaint,
                textSize = textSize.toPx(), textColor = waveColor.toArgb()
            )
            wavePath.reset()
            wavePath.moveTo(-waveWidth + animateValue, circleSizePx / 2.3f)

            var i = -waveWidth
            while (i < circleSizePx + waveWidth) {
                wavePath.relativeQuadraticBezierTo(waveWidth / 4f, -waveHeight, waveWidth / 2f, 0f)
                wavePath.relativeQuadraticBezierTo(
                    waveWidth / 4f,
                    waveHeight,
                    waveWidth / 2f,
                    0f ,
                )
                i += waveWidth
            }

            wavePath.lineTo(circleSizePx, circleSizePx)
            wavePath.lineTo(0f, circleSizePx)
            wavePath.close()

            val resultPath = Path.combine(
                path1 = circlePath, path2 = wavePath, operation = PathOperation.Intersect
            )
            drawPath(path = resultPath, color = waveColor)
            //用于根据指定的路径（Path）对绘制内容进行裁剪
            clipPath(path = resultPath, clipOp = ClipOp.Intersect){
                drawTextToCenter(text = text,
                    textPaint = textPaint,
                    textSize = textSize.toPx(),
                    textColor = downTextColor.toArgb())
            }

        }

    }

}

private fun DrawScope.drawTextToCenter(
    text: String, textPaint: android.graphics.Paint, textSize: Float, textColor: Int
) {
    textPaint.textSize = textSize
    textPaint.color = textColor

    val fontMetrics = textPaint.fontMetrics
    val x = size.width / 2f
    val y = size.height / 2f - (fontMetrics.top + fontMetrics.bottom) / 2f
    drawContext.canvas.nativeCanvas.drawText(text, x, y, textPaint)


}