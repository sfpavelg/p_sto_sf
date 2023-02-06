const sidebarText = `
<div class="side-bar">
  <div class="logo-name-wrapper">
    <div class="logo-name">
      <img
        src="./assets/images/logo.png"
        class="logo"
        alt="logo app"
        srcset=""
      />
      <span class="logo-name__name">JMStack</span>
    </div>
  </div>
  <ul class="features-list">
    <li class="features-item home">
      <i class="bx bx-trash features-item-icon"></i>
      <span class="features-item-text">Home</span>
      <span class="tooltip">Home</span>
    </li>
    <li class="features-item questions">
      <i class="bx bx-file-blank features-item-icon"></i>
      <span class="features-item-text">Questions</span>
      <span class="tooltip">Questions</span>
    </li>
    <li class="features-item tags">
      <i class="bx bx-file-blank features-item-icon"></i>
      <span class="features-item-text">Tags</span>
      <span class="tooltip">Tags</span>
    </li>
    <li class="features-item users">
      <i class="bx bx-star features-item-icon"></i>
      <span class="features-item-text">Users</span>
      <span class="tooltip">Users</span>
    </li>
    <li class="features-item companies">
      <i class="bx bx-send features-item-icon"></i>
      <span class="features-item-text">Companies</span>
      <span class="tooltip">Companies</span>
    </li>
  </ul>
</div>
`;
document.getElementById("sidebar").innerHTML = sidebarText;

// {
//     let sideBar = document.querySelector('.side-bar');
//     let arrowCollapse = document.querySelector('#logo-name__icon');
//     sideBar.onclick = () => {
//         sideBar.classList.toggle('collapse');
//         arrowCollapse.classList.toggle('collapse');
//         if (arrowCollapse.classList.contains('collapse')) {
//             arrowCollapse.classList =
//                 'bx bx-arrow-from-left logo-name__icon collapse';
//         } else {
//             arrowCollapse.classList = 'bx bx-arrow-from-right logo-name__icon';
//         }
//     };
// }