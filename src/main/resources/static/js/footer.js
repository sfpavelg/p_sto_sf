const footerText = `
<footer class="navbar fixed-bottom navbar-dark bg-secondary">
    <p class="col-md-4 mb-0 text-white">&copy; 2023 Kata Academy</p>
    <ul class="nav col-md-4 justify-content-end">
        <li class="nav-item"><a href="#" class="nav-link px-2 text-white">Home</a></li>
        <li class="nav-item"><a href="#" class="nav-link px-2 text-white">Questions</a></li>
        <li class="nav-item"><a href="#" class="nav-link px-2 text-white">Tags</a></li>
        <li class="nav-item"><a href="#" class="nav-link px-2 text-white">Users</a></li>
        <li class="nav-item"><a href="#" class="nav-link px-2 text-white">Companies</a></li>
    </ul>
</footer>
`;

let footer = document.createElement("div");
footer.innerHTML = footerText;
document.body.insertAdjacentElement('beforeend', footer);