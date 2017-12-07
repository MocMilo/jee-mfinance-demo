<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<body>
<div><b>Investment Indicators analysis results:</b></div>
<div>
    <table style="width:100%">
        <tr align="left">
            <th>
                <p>Investment: <b>${analysisResult.name}</b></p>
                <p>quotations:</p>
                <p>first: <b>${analysisResult.firstQuotation.close} PLN</b> (${analysisResult.firstQuotation.date})</p>
                <p>last: <b>${analysisResult.lastQuotation.close} PLN</b> (${analysisResult.lastQuotation.date})</p>
            </th>
            <th>
                <p><b>maxDeltaPlus</b></p>
                <p><b>${analysisResult.maxDeltaPlus.date}</b></p>
                <p><b>${analysisResult.maxDeltaPlus.close} PLN </b></p>
                <p>change: <b>${analysisResult.maxDeltaPlus.deltaClose}%</b></p>
            </th>
            <th>
                <p><b>maxDeltaMinus</b></p>
                <p><b>${analysisResult.maxDeltaMinus.date}</b></p>
                <p><b>${analysisResult.maxDeltaMinus.close} PLN</b></p>
                <p>change: <b>${analysisResult.maxDeltaMinus.deltaClose}%</b></p>
            </th>
            <th>
                <p><b>maxValueQuot</b></p>
                <p><b>${analysisResult.maxValueQuotation.date}</b></p>
                <p><b>${analysisResult.maxValueQuotation.close} PLN</b></p>
                <p>change: <b>${analysisResult.maxValueQuotation.deltaClose}%</b></p>
            </th>
            <th>
                <p><b>minValueQuot</b></p>
                <p><b>${analysisResult.minValueQuotation.date}</b></p>
                <p><b>${analysisResult.minValueQuotation.close} PLN</b></p>
                <p>change: <b>${analysisResult.minValueQuotation.deltaClose}%</b></p>
            </th>
        </tr>
    </table>
</div>
</body>
</html>