<%@ include file="header.jspf" %>

<div class="form-box">
  <h1>Log in</h1>
  <c:if test="${not empty error}"><p class="error">${error}</p></c:if>
  <form method="post" action="${ctx}/login">
    <label>Email <input type="email" name="email" required autofocus></label>
    <label>Password <input type="password" name="password" required></label>
    <button class="btn btn-dark full">Log in</button>
  </form>
  <p class="muted">No account? <a class="link" href="${ctx}/register">Sign up</a></p>
</div>

<%@ include file="footer.jspf" %>
