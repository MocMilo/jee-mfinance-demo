<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ attribute name="revenueWrapper" type="com.infoshare.web.services.logic.analysis.investmentrevenue.ContentWrapper"
              required="true" %>--%>
<%@ attribute name="analysisResult" type="com.infoshare.web.model.results.WebInvestmentRevenueResult"
              required="true" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>


<div>

    </br>
    <p><b>Result: </b></p>
    <p>Capital Revenue: <b>${analysisResult.capitalRevenueValue}</b> [PLN]</p>
    <%--fixme--%>
    <%--<p>Capital Revenue Delta: <b>${revenueWrapper.result.capitalRevenueDeltaPrecentValue}</b> [%]</p>--%>
    </br>
</div>
