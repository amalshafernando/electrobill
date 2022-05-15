$(document).ready(function() {

	$("#alertSuccess").hide();

	$("#alertError").hide();
});

//SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validateBillForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
	var type = ($("#hidBillIDSave").val() == "") ? "POST" : "PUT";
	$.ajax({
		url : "BillAPI",
		type : type,
		data : $("#formBill").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onBillSaveComplete(response.responseText, status);
		}
	});
});

function onBillSaveComplete(response, status) {
	if (status == "success") {
		
		
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			
			$("#divBillGrid").html(resultSet.data);
		}
		 else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
		
	}
	
	 else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} 
	else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	
	//reset the form
	$("#hidBillIDSave").val("");
	$("#formBill")[0].reset();
}

//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) {
	
	$("#hidItemIDSave").val($(this).data("billid"));
	$("#customerID").val($(this).closest("tr").find('td:eq(0)').text());
	$("#customerName").val($(this).closest("tr").find('td:eq(1)').text());
	$("#bill_date").val($(this).closest("tr").find('td:eq(2)').text());
	$("#units").val($(this).closest("tr").find('td:eq(3)').text());
	$("#total").val($(this).closest("tr").find('td:eq(4)').text());

});

//Delete=============================================
$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "BillAPI",
		type : "DELETE",
		data : "billID=" + $(this).data("billID"),
		dataType : "text",
		complete : function(response, status) {
			onItemDeleteComplete(response.responseText, status);
		}
	});
});

function onBillDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

//CLIENTMODEL=============================================================================================================================================
function validateBillForm() {

	// CODE
	if ($("#customerID").val().trim() == "") {
		return "Insert customer ID.";
	}
	if ($("#customerName").val().trim() == "") {
		return "Insert customer Name.";
	}
	/*if ($("#bill_date").val().trim() == "") {
		return "Insert Bill date.";
	}*/
	if ($("#units").val().trim() == "") {
		return "Insert consumed units.";
	}
	if ($("#total").val().trim() == "") {
		return "Total";
	}

	return true;
}