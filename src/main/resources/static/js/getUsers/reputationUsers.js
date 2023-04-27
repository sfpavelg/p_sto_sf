import { fetchDataByPage } from "../api/fetchDataByPage.js";

const path = "/api/user/reputation";
const itemsPerPage = 8;
let currentPage = 1;

init();

// Первая инициализация страницы
async function init() {
  await fetchDataByPage(path, currentPage, itemsPerPage).then(function (data) {
    buildHtmlUserElements(data);
    buildHtmlPageElement(data);
    changePageHandler();
  });
}

// Обновление данных пользователей на основе текущей страницы
async function updateUsersData() {
  await fetchDataByPage(path, currentPage, itemsPerPage).then(function (data) {
    buildHtmlUserElements(data);
    buildHtmlPageElement(data);
    changePageHandler();
  });
}

// Наполнение HTML элементов users-card
function buildHtmlUserElements(data) {
  let temp = "";
  data.items.forEach((user) => {
    temp += `
        <div class="card border-secondary" style="margin: 10px; padding: 5px; max-width: 290px;">
        <div class="container-fluid content-center">
            <div class="row mt-3">
                <div class="col-auto">
                    <img id="user-img" src="${user.imageLink}"
                         width="75" height="75" class="rounded">
                </div>
                <div class="col">
                    <div class="row">
                        <a href="#" style="font-size: large;">${user.fullName}</a>
                    </div>
                    <div class="row">
                        <a style="color: gray;">${user.city}</a>
                    </div>
                    <div class="row">
                        <b style="color: gray;">${user.reputation}</b>
                    </div>
                    <div>
                        <p>
                            <a href="#">tag</a>
                            ,
                            <a href="#">tag2</a>
                            ,
                            <a href="#">tag3</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
        `;
  });

  document.getElementById("users-card").innerHTML = temp;
}

// Наполнение HTML элемента pagination-container
function buildHtmlPageElement(data) {
  const totalPageCount = data.totalPageCount;
  let temp = `
  <li class="page-item ${
    currentPage === 1 ? "disabled" : "active"
  }" id="previous"> 
    <a href="previous" class="page-link" id="previous-link">Предыдущая</a>
  </li>`;

  for (let i = 1; i <= totalPageCount; i++) {
    temp += `
    <li class="page-item ${i === currentPage ? "active" : ""}" id="page-${i}">
      <a class="page-link" href="${i}" data-page="${i}">${i}</a>
    </li>
    `;
  }

  temp += `
  <li class="page-item ${
    currentPage === totalPageCount ? "disabled" : "active"
  }" id="next">
    <a href="next" class="page-link" id="next-link">Следующая</a>
  </li>`;

  document.getElementById("pagination-container").innerHTML = temp;
}

// Обработка события клика на кнопках пагинации
function changePageHandler() {
  const previousPageLink = document.getElementById("previous-link");
  const nextPageLink = document.getElementById("next-link");
  const pageLinks = document.querySelectorAll("[data-page]");

  previousPageLink.addEventListener("click", (event) => {
    event.preventDefault();
    currentPage--;
    updateUsersData();
  });

  nextPageLink.addEventListener("click", (event) => {
    event.preventDefault();
    currentPage++;
    updateUsersData();
  });

  pageLinks.forEach((pageLink) => {
    pageLink.addEventListener("click", (event) => {
      event.preventDefault();
      currentPage = parseInt(pageLink.dataset.page);
      updateUsersData();
    });
  });
}
