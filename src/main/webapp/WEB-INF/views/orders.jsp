<%@ include file="header.jspf" %>

<h1 class="page-title">My orders</h1>

<c:choose>
  <c:when test="${empty orders}">
    <p class="empty">You haven't bought anything yet. <a class="link" href="${ctx}/">Go shopping</a></p>
  </c:when>
  <c:otherwise>
    <table>
      <tr><th>#</th><th>Product</th><th>Price</th><th>Date</th></tr>
      <c:forEach var="o" items="${orders}">
        <tr>
          <td class="muted">${o.id}</td>
          <td><c:out value="${o.productName}"/></td>
          <td>${o.priceText}</td>
          <td class="muted">${o.purchasedAt}</td>
        </tr>
      </c:forEach>
    </table>
  </c:otherwise>
</c:choose>

<%@ include file="footer.jspf" %>
