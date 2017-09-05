<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="appMode" type="com.infoshare.web.webconfiguration.AppMode" required="true" %>
<c:choose>
    <c:when test="${appMode.slave}">
        <img src="../../resources/icons/banner_top_gray.jpg">
        <P><b><font color="red">WARNING! APP SLAVE MODE! </font> Customers Service Disabled.</b></P><br>
    </c:when>
    <c:otherwise>
        <img src="../../resources/icons/banner_top.jpg">
    </c:otherwise>
</c:choose>

