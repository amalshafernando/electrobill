<%@page import="com.Bill"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html> 
    <html> 
        <head>
            <meta charset="ISO-8859-1">
            <title>Bill Management</title>
            <link rel="stylesheet" href="Views/bootstrap.min.css">
            <script src="Components/jquery-3.2.1.min.js"></script>
            <script src="Components/bill.js"></script>  
            
          
        </head>
        <body> 
             <div class="container"> <div class="row"><div class="col-6">

            <h1>Bill Management V10.1</h1>

            <form id="formItem" name="formItem">

            Customer ID:
            <input id="customerCode" name="customerCode" type="text"
            class="form-control form-control-sm">  <br> 

            Customer name:
            <input id="customerName" name="customerName" type="text"
            class="form-control form-control-sm"><br>

            Billing Date:
            <input id="month" name="month" type="date"
            class="form-control form-control-sm"> <br> 

            Units consumed:
            <input id="units" name="units" type="number"
            class="form-control form-control-sm">  <br>

            <input id="btnSave" name="btnSave" type="button" value="Save"
            class="btn btn-primary">

            <input type="hidden" id="hidItemIDSave"
            name="hidItemIDSave" value="">

            </form>

            <div id="alertSuccess" class="alert alert-success"></div>
            <div id="alertError" class="alert alert-danger"></div> <br>
            <div id="divItemsGrid"> 
                
                <%
                Bill BillObj = new Bill();
                out.print(BillObj.readBills());
                %>
              </div>
            </div>
          </div>
        </div>  
        </body>
    </html>
