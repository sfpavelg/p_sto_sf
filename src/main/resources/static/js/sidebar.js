const sidebarText = `
<aside>
  <div class="d-flex flex-column flex-shrink-0 p-3 text-bg-dark fixed-top" style="width: 260px; height: 100%; margin-top: 72px;">
    <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
      <svg class="bi pe-none me-2" width="40" height="32"><use xlink:href="#bootstrap"/></svg>
      <span class="fs-4">JMStack</span>
    </a>
    <hr>
    <ul class="nav nav-pills flex-column mb-auto">
      <li class="nav-item">
        <a href="#" class="nav-link active" aria-current="page">
          <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#home"/></svg>
          Home
        </a>
      </li>
      <li>
        <a href="#" class="nav-link text-white">
          <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#speedometer2"/></svg>
          Questions
        </a>
      </li>
      <li>
        <a href="/tags" class="nav-link text-white">
          <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#table"/></svg>
          Tags
        </a>
      </li>
      <li>
        <a href="/users" class="nav-link text-white">
          <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#grid"/></svg>
          Users
        </a>
      </li>
      <li>
        <a href="#" class="nav-link text-white">
          <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#people-circle"/></svg>
          Companies
        </a>
      </li>
    </ul>
  </div>
</aside>
`;

let sidebar = document.createElement("div");
sidebar.innerHTML = sidebarText;
document.body.insertAdjacentElement('afterbegin', sidebar);