<%@ include file="header.jspf" %>

<div class="form-box">
  <h1>Admin login</h1>
  <c:if test="${not empty error}"><p class="error">${error}</p></c:if>
  <form method="post" action="${ctx}/admin/login">
    <label>Username <input name="username" required autofocus></label>
    <label>Password <input type="password" name="password" required></label>
    <button class="btn btn-dark full">Log in</button>
  </form>
</div>

<%@ include file="footer.jspf" %>
