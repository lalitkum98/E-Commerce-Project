var rootURL = "http://localhost:8090/Commerce/rest/Commerce";
function ProductViewModel() {
	var self = this;
	var data = [];
	self.productId = ko.observable();
	self.productName = ko.observable();
	self.productDescription = ko.observable();
	self.productBrand = ko.observable();
	self.products = ko.observableArray();
	self.productImage = ko.observable();
	self.uploadedImage = ko.observable();
	self.CreateProduct = function() {
		$.ajax({
			type : 'POST',
			contentType : 'application/json',
			url : rootURL + "/createProduct",
			dataType : "json",
			data : JSON.stringify({
				"productId" : this.productId(),
				"productName" : this.productName(),
				"productDescription" : this.productDescription(),
				"productBrand" : this.productBrand()
			}),
			success : function(data, textStatus, jqXHR) {
				alert('Product created successfully');
				self.GetAllProducts();
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert('product error: ' + textStatus);
			}
		});
	};

	self.GetAllProducts = function() {
		$.ajax({
			type : 'GET',
			url : rootURL + "/getAllProducts",
			dataType : "json",
			success : function(data) {
				var observableData = ko.mapping.fromJS(data);
				var array = observableData();
				self.products(array);
			}
		});
	};
	self.GetAllProducts();
	
	self.uploadProductImage = function(element, event) {
		var image = event.target;
		var reader = new FileReader();
		reader.readAsDataURL(image.files[0]);
		reader.onload = function() {
			var dataURL = reader.result;
			self.uploadedImage(dataURL);
		};

		  $.ajax({
			type : 'POST',
			url : rootURL + "/uploadProductImage",
			data : formData,
            cache : false,
            contentType : false,
            processData : false,
			success : function() {
				alert("Image uploaded");
			}
		});
	};
}
ko.applyBindings(new ProductViewModel());
