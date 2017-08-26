package com.admin.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.admin.hibernate.POJO.Product;
import com.connection.hibernate.HibernateConnectionUtil;

public class ProductService {
	SessionFactory sessionFactory = HibernateConnectionUtil.getSessionFactory();
	public Product createProduct(Product product) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.getTransaction().setTimeout(120);
		session.save(product);
		session.getTransaction().commit();
		session.close();
		return product;
	}
	
	public List<Product> getAllProducts(){
		List<Product> products = new ArrayList<Product>();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.getTransaction().setTimeout(120);
		List allProduct= session.createQuery("From Product").list();
		Iterator iterator = allProduct.iterator();
		while(iterator.hasNext()){
			Product product  = (Product) iterator.next();
			//String imageName = product.getProductImgBind();
			//product.setProductImgBind("productImages/" + imageName);
			products.add(product);
		}
		session.getTransaction().commit();
		
		session.close();
		return products;
	}
	public void deleteProduct(Product product) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.getTransaction().setTimeout(120);
		session.delete(product);
		deleteImage(product.getProductImgBind());
		session.getTransaction().commit();
		session.close();
	}
	
	public void uploadImage(InputStream inputstream,
			FormDataContentDisposition imagedetails) {

		String imagelocation = "D://PERSONAL//PersonalCodePractice//Commerce//WebContent//productImages//"
				+ imagedetails.getFileName();
		System.out.println("Receiving file " + imagedetails);
		FileOutputStream outstream;
		try {
			outstream = new FileOutputStream(new File(imagelocation));

			byte[] buf = new byte[16384];
			int len = inputstream.read(buf);
			while (len != -1) {
				outstream.write(buf, 0, len);
				len = inputstream.read(buf);
			}
			outstream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteImage(String ImagenameimageName) {
		String imagelocation = "D://PERSONAL//PersonalCodePractice//Commerce//WebContent//productImages//"
				+ ImagenameimageName;
		System.out.println("Deleting file " + ImagenameimageName);
		try {
			File image = new File(imagelocation);
			boolean result = Files.deleteIfExists(image.toPath());
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
