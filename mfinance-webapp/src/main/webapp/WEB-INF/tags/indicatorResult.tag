<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="contentWrapper" type="com.infoshare.web.analyzer.analysis.wrapper.ComparisonContentWrapper"
              required="true" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>


<hr>
<p><b>My criteria: ${contentWrapper.userCustomName}</b></p>
<table style="width:100%">
    <c:forEach var="content" items="${contentWrapper.ananysisContentList}">
        <tr align="left">
            <th>
                <p>Investment: <b>${content.result.name}</b></p>
                <p>quotations:</p>
                <p>first: <b>${content.result.firstQuotation.close} PLN</b> (${content.result.firstQuotation.date})</p>
                <p>last: <b>${content.result.lastQuotation.close} PLN</b> (${content.result.lastQuotation.date})</p>
            </th>
            <th>
                <p><b>maxDeltaPlus</b></p>
                <p><b>${content.result.maxDeltaPlus.date}</b></p>
                <p><b>${content.result.maxDeltaPlus.close} PLN </b></p>
                <p>change: <b>${content.result.maxDeltaPlus.deltaClose}%</b></p>
            </th>
            <th>
                <p><b>maxDeltaMinus</b></p>
                <p><b>${content.result.maxDeltaMinus.date}</b></p>
                <p><b>${content.result.maxDeltaMinus.close} PLN</b></p>
                <p>change: <b>${content.result.maxDeltaMinus.deltaClose}%</b></p>
            </th>
            <th>
                <p><b>maxValueQuot</b></p>
                <p><b>${content.result.maxValueQuotation.date}</b></p>
                <p><b>${content.result.maxValueQuotation.close} PLN</b></p>
                <p>change: <b>${content.result.maxValueQuotation.deltaClose}%</b></p>
            </th>
            <th>
                <p><b>minValueQuot</b></p>
                <p><b>${content.result.minValueQuotation.date}</b></p>
                <p><b>${content.result.minValueQuotation.close} PLN</b></p>
                <p>change: <b>${content.result.minValueQuotation.deltaClose}%</b></p>
            </th>
        </tr>
    </c:forEach>
</table>
