<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

<style>
.loader {
  position: absolute;
  left: 50%;
  top: 50%;
  border: 16px solid #f3f3f3;
  border-radius: 50%;
  border-top: 16px solid blue;
  border-right: 16px solid green;
  border-bottom: 16px solid red;
  width: 120px;
  height: 120px;
  -webkit-animation: spin 2s linear infinite;
  animation: spin 2s linear infinite;
}

@-webkit-keyframes spin {
  0% { -webkit-transform: rotate(0deg); }
  100% { -webkit-transform: rotate(360deg); }
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}
</style>

<title>Upload File Request Page</title>
</head>  
<body style="background-color: aliceblue;">
<div class="loader" style="display: none;"></div>
<div class="">
	<div class="well">
      	<form class="panel panel-default" style="text-align: center;" method="POST" id="exceluploadForm" action="importExcel" enctype="multipart/form-data">
			<input class="btn btn-success"  type="file" accept=".xlsx" name="file"> File to upload, currently only xlsx supported<br/> 
			<input class="btn btn-primary" type="button" id="uploadExcel" value="Upload"> Press here to upload the file!
		</form>	
      </div>
      <div id="excelImportDiv" class="well">
			<jsp:include page="excelData.jsp"></jsp:include>
	  </div>
</div>
	
<script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<script>
	$(document).ready(function() {
		
		$("#uploadExcel").on('click', function(e){
			e.preventDefault();
			$(".loader").show();
			var form = $("#exceluploadForm")[0];
			var data = new FormData(form);
			
			$.ajax({
				type: "POST",
				enctype: "multipart/form-data",
				url: 'importExcel',
				data : data,
				processData : false,
				contentType: false,
				success: function(result) {
					if(result != null){
						$("#excelImportDiv").html(result);
					}
					$(".loader").hide();
				},
				error: function (e) {
	                console.log("ERROR : ", e);
	            }
			});
			
		});
		
	})
</script>
</body>
</html>