let title = document.getElementById('title')
let buttonSubmit = document.getElementById('sendQuestion')
let titleBlock = document.getElementById('title-block')
let description = document.getElementsByClassName('richText-editor')
let descriptionBlock = document.getElementById('description-block')
let tags = document.getElementById('tags')
let tagLabel = document.getElementById('tagLabel')
let tagsBlock = document.getElementById('tags-block')
let tooShortTitle = document.createElement('h6')
tooShortTitle.textContent = 'Слишком короткий тайтл, попробуйте добавить больше информации'
tooShortTitle.style.color = 'red'
let tooShortDescription = document.createElement('h6')
tooShortDescription.textContent = 'Слишком короткое сообщение, попробуйте добавить больше информации'
tooShortDescription.style.color = 'red'
let needMoreTags= document.createElement('h6')
needMoreTags.textContent= 'Добавьте хотя бы один тег'
needMoreTags.style.color = 'red'
let menu = document.getElementById('dropdown-tags')
let tagsArrayForQuestion = new Array;

function validateQuestion(title, description, tags){
    if(title.value === null | title.value.length <= 1){
        titleBlock.appendChild(tooShortTitle)
        return false;
    }else{
        if(titleBlock.lastChild === tooShortTitle){
            titleBlock.removeChild(tooShortTitle)
        }
    }
    if(description[0].childNodes === null | description[0].childNodes.length <= 1){
        descriptionBlock.appendChild(tooShortDescription)
        return false;
    }else{
        if(descriptionBlock.lastChild === tooShortDescription){
            descriptionBlock.removeChild(tooShortDescription)
        }
    }
    if(tagsArrayForQuestion.length < 1){
        tagsBlock.appendChild(needMoreTags)
        return false;
    }else{
        if(tagsBlock.lastChild === needMoreTags){
            tagsBlock.removeChild(needMoreTags)
        }
    }
    return true;
}
let i = 0;
let l = 0;
function deleteTag(id){
    let cont = document.getElementById('ujeest')
    let come = document.getElementById(id).parentNode;
    let s = 0;
    for(let l = 0; l <= cont.childNodes.length - 1; l++){
        if(cont.childNodes[l].id === come.id){
            s = l;
        }
    }
    tagsArrayForQuestion.splice(s,1)
    let tagdel = document.getElementById(id)
    document.getElementById('ujeest').removeChild(tagdel.parentNode)
}
function addtag(tag){
    if(i < 1){
        let tagsDiv = document.createElement('div')
        tagsDiv.className = 'tagsDiv';
        tagsDiv.id = 'ujeest';
        tagsBlock.appendChild(tagsDiv)
        i++;
    }
    tagsArrayForQuestion.push(tag)
    let cont = document.getElementById('ujeest')
    if(cont){
        let tagDiv = document.createElement('div')
        tagDiv.className = 'tagDiv';
        tagDiv.id = l + 'tagDiv'
        l++;
        let tagDivName = document.createElement('div')
        tagDivName.className = 'tagDivName';
        let tagDelete = document.createElement('div')
        tagDelete.setAttribute('onClick', 'deleteTag(this.id)')
        tagDelete.innerHTML = '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-circle" viewBox="0 0 16 16">\n' +
            '  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>\n' +
            '  <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>\n' +
            '</svg>';
        tagDelete.className = 'tagDivDelete';
        tagDelete.id = tagDiv.id + 1;
        tagDivName.textContent = tag.name;
        cont.appendChild(tagDiv)
        tagDiv.appendChild(tagDivName)
        tagDiv.appendChild(tagDelete)
    }
}


function convertDataToJson(title, description, tags){
    let descriptionArray = description[0].innerHTML;
    let arr = new Set(tagsArrayForQuestion);
    for(let l = 0; l <= tagsArrayForQuestion.length - 1; l++){
        for(let k = 1; k<= tagsArrayForQuestion.length -1; k++){
            if(tagsArrayForQuestion[l].id === tagsArrayForQuestion[k].id && l!==k){
                tagsArrayForQuestion.splice(k, 1);
                k--;
            }
        }
    }
    let json = {
        title: title.value,
        description: descriptionArray,
        tags: tagsArrayForQuestion
    }
    return JSON.stringify(json);
}


function sendQuestion(event) {
    event.preventDefault();
    if(validateQuestion(title,description, tags) === false){
    }else{
        fetch('http://localhost:8091/api/user/question',
            {
                method: "POST",
                headers:{
                    'Authorization': 'Bearer ' + token,
                    'Content-type': 'application/json'
                },
                body: convertDataToJson(title, description, tags)
            }).then((res) =>{
            if (res.status >= 200 && res.status < 300) {
                window.location.replace('http://localhost:8091/questions')
                }
            }

        )
    }
}

function tagFilter(){
    let massTags = tags.value.split(/\,|;| /);
    return massTags.at(-1)
}

function helpmenu(){
    while(menu.firstChild){
        menu.removeChild(menu.firstChild)
    }
    let promise = new Promise(function (resolve, reject) {
        fetch("http://localhost:8091/api/user/tag/tagSearchByName?" +
            new URLSearchParams({
                    tagName: tagFilter()
            }))
            .then((response) => response.json())
            .then((json) => {
                    if (json) {
                        let data = json
                        resolve(data)
                    } else {
                        reject(console.log('data ne prishla'))
                    }
                }
            )
    })
    promise.then((data) => {
        if(data.length < 1){
            let dropDownItemNull = document.createElement('li')
            dropDownItemNull.textContent = 'Не найдено подходящих тегов'
            menu.appendChild(dropDownItemNull)
        }
        data.forEach((tag) => {
            menu.style.display = 'flex';
            let dropDownItem = document.createElement('li')
            dropDownItem.className = "dropdown-item-tag"
            let dropDownItemName = document.createElement('div')
            dropDownItemName.className = 'dropdown-item-name';
            dropDownItem.id = tag.id;
            let dropDownItemDescription = document.createElement('div')
            dropDownItemDescription.className = 'dropdown-item-description';
            dropDownItem.appendChild(dropDownItemName)
            dropDownItem.appendChild(dropDownItemDescription)
            dropDownItemDescription.textContent = tag.description
            dropDownItemName.textContent = tag.name
            menu.appendChild(dropDownItem)

            dropDownItem.addEventListener("click", ()=> {
                if(tagsBlock.lastChild === needMoreTags){
                    tagsBlock.removeChild(needMoreTags)
                }
                addtag(tag)
                tags.value = '';
            },false)
        })
    })
}


tags.addEventListener("input", helpmenu,false)
tags.addEventListener("focus", helpmenu,false)

document.addEventListener("click",function(event) {
    if (!tags.contains(event.target)) {
        menu.style.display='none';}
});

let items = document.getElementsByClassName('dropdown-item-tag');
let index = -1;
document.addEventListener('keydown', function(e) {
    function move(dir){
        !items[index] || (items[index].className = 'dropdown-item-tag');
        switch (dir)
        {
            case 'up':
                index = (index <= 0) ? items.length-1 : --index;
                break;
            case 'down':
                index = (index == items.length-1) ? 0 : ++index;
                break;
        }
        items[index].className = 'dropdown-item-tag drop-selected';
    }
    switch (e.code)
    {
        case 'ArrowUp':
            e.preventDefault();
            move('up');
            break;
        case 'ArrowLeft':
            e.preventDefault();
            move('up');
            break;
        case 'ArrowDown':
            e.preventDefault();
            move('down');
            break;
        case 'ArrowRight':
            e.preventDefault();
            move('down');
            break;
        case 'Enter':
            if(menu.style.display == 'none') break;
            let t = new Object;
            t.id = items[index].id;
            t.name = items[index].firstChild.textContent;
            t.description = items[index].lastChild.textContent;
            if(tagsBlock.lastChild === needMoreTags){
                tagsBlock.removeChild(needMoreTags)
            }
            addtag(t)
            tags.value = '';
            menu.style.display = 'none';
            index = -1;
            break;
        case 'Escape':
            menu.style.display = 'none';
            break;
    }
},false);

buttonSubmit.addEventListener("click",sendQuestion,false)