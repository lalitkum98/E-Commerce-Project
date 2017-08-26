var rootURL = "http://localhost:8090/Commerce/rest/Commerce";

var  ProductViewModel = function() {
	var self = this;
	self.productId = ko.observable();
	self.productName = ko.observable();
	self.productDescription = ko.observable();
	self.productBrand = ko.observable();
	//self.productImage = ko.observable();
	//self.showProductImage = ko.observable();
	self.uploadedImage = ko.observable();
	self.productImgBind = ko.observable();
	self.products = ko.observableArray();
	self.catalogTabOn = ko.observable("on");
	self.settingTabOn = ko.observable(null);
	self.publishTabOn = ko.observable(null);
	self.designTabOn = ko.observable(null);
    self.GetAllProducts();
};


ProductViewModel.prototype.deleteProduct = function(product) {
	var self = this;
    self.products.remove(product);
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : rootURL + "/deleteProduct",
		dataType : "json",
		data : JSON.stringify({
			"productId" : product.productId(),
			"productImgBind": product.productImgBind()
		}),
		success : function(data, textStatus, jqXHR) {
			alert('Product Deleted successfully');
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert('Error while deleting Product: ' + textStatus);
		}
	});
/*	$.ajax({
		type : 'DELETE',
		url : rootURL + "/deleteProduct/"+ product.productId(),
		success : function(data, textStatus, jqXHR) {
			alert('Product Deleted successfully');
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert('Error while deleting Product: ' + textStatus);
		}
	});*/
    
};
ProductViewModel.prototype.CreateProduct = function() {
	var self = this;
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : rootURL + "/createProduct",
		dataType : "json",
		data : JSON.stringify({
			"productId" : this.productId(),
			"productName" : this.productName(),
			"productDescription" : this.productDescription(),
			"productBrand" : this.productBrand(),
			"productImgBind": this.productImgBind()
		}),
		success : function(data, textStatus, jqXHR) {
			alert('Product created successfully');
			self.GetAllProducts();
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert('Error while creating Product: ' + textStatus);
		}
	});
};
ProductViewModel.prototype.GetAllProducts = function() {
	var self = this;
	$.ajax({
		type : 'GET',
		url : rootURL + "/getAllProducts",
		dataType : "json",
		success : function(data, textStatus, jqXHR) {
			var observableData = ko.mapping.fromJS(data);
			var array = observableData();
			//alert(array);
			self.products(array);
			//self.showProductImage("D://PERSONAL//PersonalCodePractice//Commerce//WebContent//productImages//" + array.productImgBind);
			console.log("Method() GetAllProducts : Got all the products");
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert('Error while getting AllProducts' + textStatus);
	}
	});	
	};

ProductViewModel.prototype.uploadProductImage = function(element, event) {
	var self = this;
	var image = event.target;
	var reader = new FileReader();
	reader.readAsDataURL(image.files[0]);
	reader.onload = function() {
		var dataURL = reader.result;
		self.uploadedImage(dataURL);
	};
	var objFormData = new FormData();
	var objFile = image.files[0];
	self.productImgBind(objFile.name);
	objFormData.append('productImageFile', objFile);
	  $.ajax({
		type : 'POST',
		url : rootURL + "/uploadProductImage",
		data : objFormData,
		dataType: 'json',
		enctype: 'multipart/form-data',
        cache : false,
        contentType : false,
        processData : false,
        success : function(data, textStatus, jqXHR) {
			alert("Image uploaded successfully");
		},
	  	error : function(jqXHR, textStatus, errorThrown) {
			alert('Error while uploading Image: ' + textStatus);
		}
	});
};





ProductViewModel.prototype.catalogTab = function (){
	var self = this;
	self.catalogTabOn("on");
	self.settingTabOn(null);
	self.publishTabOn(null);
	self.designTabOn(null);
};

ProductViewModel.prototype.settingTab = function (){
	var self = this;
	self.catalogTabOn(null);
	self.settingTabOn("on");
	self.publishTabOn(null);
	self.designTabOn(null);
};

ProductViewModel.prototype.publishTab = function (){
	var self = this;
	self.catalogTabOn(null);
	self.settingTabOn(null);
	self.publishTabOn("on");
	self.designTabOn(null);
	
};

ProductViewModel.prototype.designTab = function (){
	var self = this;
	self.catalogTabOn(null);
	self.settingTabOn(null);
	self.publishTabOn(null);
	self.designTabOn("on");
};



ko.applyBindings(new ProductViewModel());
