<%@ include file="header.jspf" %>

<div class="form-box">
  <h1>Create account</h1>
  <c:if test="${not empty error}"><p class="error">${error}</p></c:if>
  <form method="post" action="${ctx}/register">
    <label>Name <input name="name" required autofocus></label>
    <label>Email <input type="email" name="email" required></label>
    <label>Password <input type="password" name="password" minlength="6" required></label>
    <button class="btn btn-dark full">Sign up</button>
  </form>
  <p class="muted">Already have an account? <a class="link" href="${ctx}/login">Log in</a></p>
</div>

<%@ include file="footer.jspf" %>
