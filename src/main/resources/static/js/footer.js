const footerText = `
<footer class="py-3 text-bg-dark" style="width: 100vw; flex: 0 0 auto;">
    <ul class="nav justify-content-center border-bottom pb-3 mb-3">
      <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Home</a></li>
      <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Questions</a></li>
      <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Tags</a></li>
      <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Users</a></li>
      <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Companies</a></li>
      <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">About</a></li>
      <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Products</a></li>
      <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">For Teams</a></li>
      <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">FAQs</a></li>
    </ul>
    <p class="text-center text-muted">&copy; 2023 JMStack, Kata Academy</p>
 </footer>
`;

let footer = document.createElement("div");
footer.innerHTML = footerText;
document.body.insertAdjacentElement('beforeend', footer);