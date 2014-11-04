<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">

</script>
</head>
<body>

<h1>Ideal Price Calculated For the Select Product</h1>

<p>Ideal Price. This price is calculated by taking all the prices of this product, 
removing the 2 highest and 2 lowest, then doing an average with the rest and adding 20% to it. 
It is known that this complicated formula will be changed often. 
Even different installations of Prizy Pricer will be using different versions of this ideal price formula.
So the key here is to try to minimize the impact to the application when a new formula needs to be put in place</p>

<h6> Assumed that 20% of the average should be added</h6>

<table id="example" class="display" cellspacing="1" width="100%" border="1">
		<thead>
			<tr>
				<th>BarCode</th>
				<th>ProductDesc</th>
				<th>LowerPrice</th>
				<th>HighestPrice</th>
				<th>AveragePrice</th>
				<th>IdealPrice</th>
				<th>NumOfPricesCollected</th>
			</tr>
		</thead>
		<tbody>
		<tr>
				<td>${BarCode}</td>
				<td>${ProductDesc}</td>
				<td>${LowerPrice}</td>
				<td>${HighestPrice}</td>
				<td>${AveragePrice}</td>
				<td>${IdealPrice}</td>
				<td>${NumOfPricesCollected}</td>
			</tr>
		</tbody>
	</table>
</body>
</html>