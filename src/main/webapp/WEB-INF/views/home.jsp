<%@ include file="header.jspf" %>

<section class="hero">
  <h1>Simple things,<br>simply sold.</h1>
  <p class="muted">A small store of everyday basics. Pick something, press buy.</p>
</section>

<h2>Products</h2>
<div class="grid">
  <c:forEach var="p" items="${products}">
    <div class="card">
      <div class="card-top">
        <h3><c:out value="${p.name}"/></h3>
        <span class="price">${p.priceText}</span>
      </div>
      <p class="muted"><c:out value="${p.description}"/></p>
      <form method="post" action="${ctx}/buy">
        <input type="hidden" name="productId" value="${p.id}">
        <button class="btn">Buy</button>
      </form>
    </div>
  </c:forEach>
</div>

<%@ include file="footer.jspf" %>
