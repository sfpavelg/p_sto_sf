const headerText = `
<header class="p-3 text-bg-dark fixed-top" style="height: 72px;">
  <div class="container">
    <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
      <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
        <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap">
          <use xlink:href="#bootstrap" />
        </svg>
      </a>
      <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
        <li>
          <a href="#" class="nav-link px-2 text-secondary">About</a>
        </li>
        <li>
          <a href="#" class="nav-link px-2 text-white">Products</a>
        </li>
        <li>
          <a href="#" class="nav-link px-2 text-white">For Teams</a>
        </li>
        <li>
          <a href="#" class="nav-link px-2 text-white">FAQs</a>
        </li>
      </ul>
      <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" role="search">
        <input type="search" class="form-control form-control-dark text-bg-dark" placeholder="Search..." aria-label="Search">
      </form>
      <div class="dropdown">
        <a href="#" class="d-flex align-items-center text-white text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
          <img src="https://kata.academy/images/about/logo.svg" alt="" width="32" height="32" class="rounded-circle me-2">
          <strong></strong>
        </a>
        <ul class="dropdown-menu dropdown-menu-dark text-small shadow">
          <li>
            <a class="dropdown-item" href="#">New project...</a>
          </li>
          <li>
            <a class="dropdown-item" href="#">Settings</a>
          </li>
          <li>
            <a class="dropdown-item" href="#">Profile</a>
          </li>
          <li>
            <hr class="dropdown-divider">
          </li>
          <li>
            <a class="dropdown-item" href="#">Sign out</a>
          </li>
        </ul>
      </div>
      <button id="logoutBtn" type="button" class="btn btn-primary ms-3">LogOut</button>
    </div>
  </div>
</header>
`;

let header = document.createElement("div");
header.innerHTML = headerText;
document.body.insertAdjacentElement('afterbegin', header);

logoutInit();

function logoutInit(){
    document.getElementById('logoutBtn').onclick = function (){
        localStorage.removeItem(keyName);
        location.href = '/login'
    }
}