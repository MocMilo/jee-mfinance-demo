<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="allInvRevCrit" type="java.util.List"
              required="true" %>

<p><b>User criteria statistics</b></p>
<table style="width:100%">
    <tr>
        <th>Id</th>
        <th>Investment name</th>
        <th>User favourite</th>
        <th>Buy Date</th>
        <th>Sell Date</th>
        <th>User Custom Name</th>
        <th>Created</th>
        <th>Updated</th>
    </tr>
    <c:forEach var="criteria" items="${allInvRevCrit}">
        <tr>
            <th>${criteria.id}</th>
            <th>${criteria.investmentName}</th>
            <th>${criteria.favourite}</th>
            <th>${criteria.buyDate}</th>
            <th>${criteria.sellDate}</th>
            <th>${criteria.userCustomName}</th>
            <th>${criteria.creationDateTime}</th>
            <th>${criteria.lastUpdateDateTime}</th>
        </tr>
    </c:forEach>
</table>
