<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="contentWrapper" type="com.infoshare.web.analyzer.analysis.investmentrevenue.ContentWrapper"
              required="true" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<div>
<form method="post" action="../userview/favouriterevenue">
    criteria id ${contentWrapper.criteria.id}
    <input type="text" name="userCustomName" placeholder="user custom name"/>
    <input type="text" name="criteriaId" value="${contentWrapper.criteria.id}" hidden/>
    <button type="submit" name="updateAction" >ok</button>
    <button type="submit" name="deleteAction" >remove</button>
</form>
</div>