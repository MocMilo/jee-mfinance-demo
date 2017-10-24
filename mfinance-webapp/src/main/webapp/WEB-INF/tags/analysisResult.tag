<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="revenueWrapper" type="com.infoshare.web.analyzer.analysis.investmentrevenue.ContentWrapper"
              required="true" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>


<div>
<p><b>User input data: </b></p>
<p>Investment name: <b>${revenueWrapper.criteria.investmentName}</b></p>
<p>Invested capital: <b>${revenueWrapper.criteria.investedCapital}</b></p>
<p>Buy date: <b>${revenueWrapper.criteria.buyDate}</b></p>
<p>Sell date: <b>${revenueWrapper.criteria.sellDate}</b></p>
<p>marked as favourite: <b>${revenueWrapper.result.finallyEvaluatedInput.favourite}</b></p>

<tags:systemMessage systemMessage="${revenueWrapper.message}"/>

<p><b>User input moderation report:</b></p>

<p>buy date: <b>${revenueWrapper.result.finallyEvaluatedInput.buyDate}</b></p>
<p>sell date: <b>${revenueWrapper.result.finallyEvaluatedInput.sellDate}</b></p>


</br>
<p><b>Result: </b></p>
<p>Capital Revenue: <b>${revenueWrapper.result.capitalRevenueValue}</b> [PLN]</p>
<p>Capital Revenue Delta: <b>${revenueWrapper.result.capitalRevenueDeltaPrecentValue}</b> [%]</p>
</br>
</div>
