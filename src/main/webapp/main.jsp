<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC>
<html>
    <jsp:include page="WEB-INF/gral-head.jsp"/>
    <body>
        <jsp:include page="WEB-INF/gral-header_ini.jsp"/>
        <jsp:include page="WEB-INF/gral-header_fin_on.jsp"/>
        
        <c:choose >
            <c:when test="${cardsCorredores != null && !lista.isEmpty()}">
                <div class="row g-4 mt-3 row-cols-1 row-cols-sm-2 row-cols-lg-3 row-cols-xl-4 row-cols-xxl-5 row-marg" data-masonry='{"percentPosition": true }'>
                    <jsp:include page="WEB-INF/cardsCorredores.jsp"/>
                </div>
            </c:when>
            <c:otherwise>
                <div class="row mt-4">
                    <div class="col-12">
                        <p class="display-5 text-danger">Ooops! Parece que no hay corredores...</p>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
        
    </body>
</html>
