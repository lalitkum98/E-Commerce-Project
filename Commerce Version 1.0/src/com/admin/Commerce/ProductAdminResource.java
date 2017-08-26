package com.admin.Commerce;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


/*import org.glassfish.jersey.media.multipart.FormDataContentDisposition;  
import org.glassfish.jersey.media.multipart.FormDataParam;*/  
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.admin.hibernate.POJO.Product;
import com.admin.services.ProductService;


@Path("/Commerce")
public class ProductAdminResource {
	ProductService productService = new ProductService();
	 
	@POST
	@Path("/createProduct")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Product createProduct(Product product) {
		
		return productService.createProduct(product);
		
	}
	@GET
	@Path("/getAllProducts")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Product> GetAllProducts(){
		return productService.getAllProducts();
	}
	
	/*@POST
	@Path("/uploadProductImage")
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	public void uploadProductImage(   @FormDataParam("productImage") InputStream inputstream, 
									  @FormDataParam("productImage") FormDataContentDisposition imagedetails){
		 if(imagedetails==null || imagedetails.getFileName()==null) {
		       System.out.println("its null");
		    }
		 
		 	String imagelocation = "D://PERSONAL//PersonalCodePractice//Commerce//WebContent//productImages" + imagedetails.getFileName();  
		    System.out.println("Receiving file "+imagedetails.getFileName());
		    FileOutputStream outstream;
			try {
				outstream = new FileOutputStream(new File(imagelocation));
			
		    byte[] buf = new byte[16384];
		    int len = inputstream.read(buf);
		    while(len!=-1) {
		    	outstream.write(buf,0,len);
		        len = inputstream.read(buf);
		    }
		    outstream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  
	}*/
	
}
