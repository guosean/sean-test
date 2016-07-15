package com.mahout;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.junit.Before;
import org.junit.Test;

/**
 * @AUTHOR: guozb
 * @DATE: 2014-7-10
 */
public class TestMahout {
	private URL modelUrl;
	@Before
	public void setup(){
		modelUrl = TestMahout.class.getResource("/test.txt");
	}
	/**
	 * 基于用户相似度的协同过滤
	 */
	@Test
	public void testBaseUserCF() {
		// 1、构建模型
		DataModel dm;
		try {
			dm = new FileDataModel(new File(modelUrl.getFile()));
			// 2、计算相似度
			UserSimilarity us = new PearsonCorrelationSimilarity(dm);
			// 3、查找K紧邻
			UserNeighborhood unh = new NearestNUserNeighborhood(2, us, dm);
			// 4、构建推荐引擎
			Recommender rc = new GenericUserBasedRecommender(dm, unh, us);
			// 为用户i推荐两个item
			for (int i = 1; i < 6; i++) {
				System.out.println("recommand for user:" + i);
				List<RecommendedItem> recommendations = rc.recommend(i, 2);
				for (RecommendedItem recommendation : recommendations) {
					System.out.println(recommendation);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 基于内容相似度的协同过滤推荐实现
	 */
	@Test
	public void testBaseItemCF() {
		DataModel model;
		try {
			model = new FileDataModel(new File(modelUrl.getFile()));
			ItemSimilarity itemsimilarity = new PearsonCorrelationSimilarity(
					model);
			Recommender recommender = new GenericItemBasedRecommender(model,
					itemsimilarity);
			List<RecommendedItem> recommendations = recommender.recommend(1, 1);
			for (RecommendedItem recommendation : recommendations) {
				System.out.println(recommendation);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
/*
	*//**
	 * 基于SlopOne的推荐实现
	 *//*
	@Test
	public void testBaseSlopOne() {
		DataModel model;
		try {
			model = new FileDataModel(new File(modelUrl.getFile()));
			Recommender recommender = new SlopeOneRecommender(model);
			List<RecommendedItem> recommendations = recommender.recommend(1, 1);
			for (RecommendedItem recommendation : recommendations) {
				System.out.println(recommendation);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Io Error");
			e.printStackTrace();
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			System.out.println("Taste Error");
			e.printStackTrace();
		}

	}*/
}
