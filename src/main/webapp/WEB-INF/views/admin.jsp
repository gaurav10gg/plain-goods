<%@ include file="header.jspf" %>

<h1 class="page-title">Dashboard</h1>

<section class="box">
  <h2>Add product</h2>
  <c:if test="${not empty error}"><p class="error">${error}</p></c:if>
  <form method="post" action="${ctx}/admin" class="row">
    <label>Name <input name="name" required></label>
    <label>Description <input name="description"></label>
    <label>Price (₹) <input type="number" name="price" min="1" step="0.01" required></label>
    <button class="btn btn-dark">Add</button>
  </form>
</section>

<h2>Products</h2>
<table>
  <tr><th>#</th><th>Name</th><th>Description</th><th>Price</th></tr>
  <c:forEach var="p" items="${products}">
    <tr>
      <td class="muted">${p.id}</td>
      <td><c:out value="${p.name}"/></td>
      <td class="muted"><c:out value="${p.description}"/></td>
      <td>${p.priceText}</td>
    </tr>
  </c:forEach>
</table>

<h2>Purchases</h2>
<c:choose>
  <c:when test="${empty purchases}"><p class="empty">No purchases yet.</p></c:when>
  <c:otherwise>
    <table>
      <tr><th>#</th><th>User</th><th>Product</th><th>Price</th><th>Date</th></tr>
      <c:forEach var="o" items="${purchases}">
        <tr>
          <td class="muted">${o.id}</td>
          <td><c:out value="${o.userEmail}"/></td>
          <td><c:out value="${o.productName}"/></td>
          <td>${o.priceText}</td>
          <td class="muted">${o.purchasedAt}</td>
        </tr>
      </c:forEach>
    </table>
  </c:otherwise>
</c:choose>

<%@ include file="footer.jspf" %>
