package com.sean.solr;

import lombok.Getter;
import lombok.Setter;

import org.apache.solr.client.solrj.beans.Field;

public class SolrBean {
	@Field
	@Getter
	@Setter
	String id;
	@Field
	@Getter
	@Setter
	String name;
	@Field
	@Getter
	@Setter
	float price;

}
