//package com.ubsoft.framework.core.util;
//
//import net.coobird.thumbnailator.Thumbnails;
//import net.coobird.thumbnailator.Thumbnails.Builder;
//import net.coobird.thumbnailator.geometry.Positions;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//
//public class ImageUtil {
//	/**
//	 * 几种常见的图片格式
//	 */
//	public static String IMAGE_TYPE_GIF = "gif";// 图形交换格式
//	public static String IMAGE_TYPE_JPG = "jpg";// 联合照片专家组
//	public static String IMAGE_TYPE_JPEG = "jpeg";// 联合照片专家组
//	public static String IMAGE_TYPE_BMP = "bmp";// 英文Bitmap（位图）的简写，它是Windows操作系统中的标准图像文件格式
//	public static String IMAGE_TYPE_PNG = "png";// 可移植网络图形
//	public static String IMAGE_TYPE_PSD = "psd";// Photoshop的专用格式Photoshop
//
//	/**
//	 * 程序入口：用于测试
//	 *
//	 * @param args
//	 */
//	public static void main(String[] args) {
//
//	}
//
//	/**
//	 * 等比例缩放图片
//	 *
//	 * @param fromPic
//	 * @param toPic
//	 * @param scale
//	 *            0.2f等比例缩小,2f 等比例放大
//	 */
//	public final static void scale(String fromPic, String toPic, float scale, String type) {
//
//		try {
//			Thumbnails.of(fromPic).scale(scale).outputFormat(type).toFile(toPic);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//
//	/**
//	 * 按照指定宽度和高度缩放图片
//	 *
//	 * @param fromPic
//	 * @param toPic
//	 * @param width
//	 * @param height
//	 */
//	public final static void scale(String fromPic, String toPic, int width, int height, String type) {
//
//		try {
//			Thumbnails.of(fromPic).size(width, height).outputFormat(type).toFile(toPic);// 变为400*300,遵循原图比例缩或放到400*某个高度
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	/**
//	 * 压缩至指定图片尺寸，保持图片不变形，多余部分裁剪掉
//	 *
//	 * @param fromPic
//	 * @param toPic
//	 * @param width
//	 * @param height
//	 */
//	public final static void scale2(String fromPic, String toPic, int width, int height, String type) {
//		try {
//			BufferedImage image = ImageIO.read(new File(fromPic));
//			Builder<BufferedImage> builder = null;
//			int imageWidth = image.getWidth();
//			int imageHeitht = image.getHeight();
//			if ((float) width / height != (float) imageWidth / imageHeitht) {
//				if (imageWidth  > imageHeitht) {
//					image = Thumbnails.of(fromPic).height(height).asBufferedImage();
//				} else {
//					image = Thumbnails.of(fromPic).width(width).asBufferedImage();
//				}
//				builder = Thumbnails.of(image).sourceRegion(Positions.CENTER, width, height).size(width, height);
//			} else {
//				builder = Thumbnails.of(image).size(width,height);
//			}
//			builder.outputFormat("jpg").toFile(toPic);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 压缩图片
//	 *
//	 * @param fromPic
//	 * @param toPic
//	 * @param quality
//	 *            压缩到原来的百分比:0.2f
//	 */
//	public final static void quality(String fromPic, String toPic, double quality, String type) {
//
//		try {
//			Thumbnails.of(fromPic).scale(1f).outputQuality(quality).outputFormat(type).toFile(toPic);
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//
//	/**
//	 * 剪切图片
//	 *
//	 * @param fromPic
//	 * @param toPic
//	 * @param width
//	 * @param height
//	 */
//	public final static void cut(String fromPic, String toPic, int width, int height, String type) {
//		try {
//			Thumbnails.of(fromPic).sourceRegion(Positions.TOP_CENTER, width, height).outputFormat(type).toFile(toPic);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 指定坐标剪切
//	 *
//	 * @param fromPic
//	 * @param toPic
//	 * @param x
//	 *            起始X坐标
//	 * @param y
//	 *            起始Y坐标
//	 * @param width
//	 * @param height
//	 */
//	public final static void cut(String fromPic, String toPic, int x, int y, int width, int height, String type) {
//		try {
//			Thumbnails.of(fromPic).sourceRegion(x, y, width, height).outputFormat(type).toFile(toPic);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 原图转化
//	 *
//	 * @param fromPic
//	 * @param toPic
//	 * @param type
//	 */
//	public final static void convert(String fromPic, String toPic, String type) {
//		try {
//			Thumbnails.of(fromPic).scale(1f).outputFormat(type).toFile(toPic);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 旋转 rotate(角度),正数：顺时针 负数：逆时针
//	 *
//	 * @param fromPic
//	 * @param toPic
//	 * @param rotate
//	 */
//	public final static void rotate(String fromPic, String toPic, int rotate) {
//		try {
//			Thumbnails.of(fromPic).scale(1.0f).rotate(rotate).toFile(toPic);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//
//	/**
//	 * 加水印
//	 * @param fromPic
//	 * @param toPic
//	 * @param waterPic
//	 */
//	public final static void water(String fromPic, String toPic, String waterPic) {
//		BufferedImage waterImage;
//		try {
//			waterImage = ImageIO.read(new File(waterPic));
//			Thumbnails.of(fromPic).scale(1.0f).watermark(Positions.CENTER, waterImage, 0.5f).outputQuality(1.0f).toFile(waterPic);
//		} catch (IOException e) {
//
//			e.printStackTrace();
//		}
//
//	}
//
//}
