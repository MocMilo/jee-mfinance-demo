<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="contentWrapper" type="java.util.List"
              required="true" %>

<p><b>Agent Tasks</b></p>
<table style="width:100%">
    <tr>
        <th>Id</th>
        <th>Task name</th>
        <th>Type</th>
        <th>Start date</th>
        <th>End date</th>
        <th>Delay</th>
        <th>Time span</th>
        <th>Active</th>
        <th>edit</th>
    </tr>
    <c:forEach var="task" items="${contentWrapper}">
        <tr>
            <th>${task.id}</th>
            <th>${task.taskName}</th>
            <th>${task.taskTypeName}</th>
            <th>${task.startDate}</th>
            <th>${task.endDate}</th>
            <th>${task.startDelay}</th>
            <th>${task.timeSpan}</th>
            <th>${task.active}</th>
            <th>
                <form action="../adminview/task" method="get">
                        <input type="hidden" name="id" value="${task.id}"/></p>
                        <button type="submit">edit</button>
                </form>
            </th>
        </tr>
    </c:forEach>
</table>