package com.sean.solr;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestService {
	private static  String baseURL = null;
	private static HttpSolrServer server = null;
    @Before 
    public void setUp(){
    	  baseURL = "http://192.168.50.135:8983/solr";
  		 server = new HttpSolrServer(baseURL );
		System.out.println("setUp");
	}
    @After
    public void tearDown() {
    	System.out.println("tearDown");
	}
	@Test 
	public void testAddDoc() throws SolrServerException, IOException{
		/*SolrInputDocument doc = new SolrInputDocument();
		doc.addField("id", "2");
		doc.addField("name", "name2");
		doc.addField("price", 100);*/
		SolrBean doc = new SolrBean();
		doc.setId("3");
		doc.setName("name3");
		doc.setPrice(11);
		server.addBean(doc);
		server.commit();
	}
	@Test  
	public void testUpdateDoc() {

	}
	public void testDeleteDoc() throws SolrServerException, IOException {
		server.deleteByQuery("*:*");
		server.commit();
	}
}
