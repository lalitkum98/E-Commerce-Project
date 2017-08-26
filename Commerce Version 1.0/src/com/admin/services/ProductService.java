package com.admin.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.admin.hibernate.POJO.Product;
import com.connection.hibernate.HibernateConnectionUtil;

public class ProductService {
	SessionFactory sessionFactory = HibernateConnectionUtil.getSessionFactory();
	public Product createProduct(Product product) {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(product);
		session.getTransaction().commit();
		session.close();
		return product;
	}
	
	public List<Product> getAllProducts(){
		List<Product> products = new ArrayList<Product>();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List allProduct= session.createQuery("From Product").list();
		Iterator iterator = allProduct.iterator();
		while(iterator.hasNext()){
			Product product  = (Product) iterator.next();
			products.add(product);
		}
		session.getTransaction().commit();
		session.close();
		return products;
	}

}
