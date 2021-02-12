'use strict';

const ListName = document.querySelector("#ListName");
const tasklist = document.querySelector("#tasks");

const ListNameRead = document.querySelector("#ListNameRead");

const ListIdUpdate = document.querySelector("#ListIdUpdate");
const ListNameUpdate = document.querySelector("#ListNameUpdate");

const ListIdDelete = document.querySelector("#ListNameDelete");

const createToDoList = () => {
    const ListName = ListName.value;

    let data = {
        "tasklist": [null],
        "name": ListName
    }

    fetch("http://localhost:8080/ToDoList/create", {
        method: "POST",
        body: JSON.stringify(data),
        headers: {
            "Content-Type": "application/json"
        }
    })
    .then(response => response.json())
    .then(info => console.log(info))
    .catch(err => console.error(`error ${err}`));
}

const readAllToDoLists = () => {
    fetch("http://localhost:8080/ToDoList/readAll")
        .then(response => response.json())
        .then(info => {
            for (let ToDoList of info) {
                console.log(ToDoList);
            }
        })
        .catch(err => console.error(`error ${err}`));
}

const readByListName = () => {
    const ListNameRead = ListNameRead.value;

    fetch(`http://localhost:8901/band/read/${ListNameRead}`)
        .then(response => response.json())
        .then(info => console.log(info))
        .catch(err => console.error(`error ${err}`));
}


const updateList = () => {
    const ListIdUpdate = ListIdUpdate.value;
    const ListNameUpdate = ListNameUpdate.value;

    let data = {
        
        "tasklist": null,
        "name": ListNameUpdate
    }

    fetch(`http://localhost:8080/ToDoList/replace/${ListIdUpdate}`, {
        method: "PUT",
        body: JSON.stringify(data),
        headers: {
            "Content-Type": "application/json"
        }
    })
    .then(response => response.json())
    .then(info => console.log(info))
    .catch(err => console.error(`error ${err}`));
}

const deleteListById = () => {
    const ListIdDelete = ListIdDelete.value;

    fetch(`http://localhost:8080/ToDoList/delete/${ListIdDelete}`, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json"
        }
    })
    .then(response => response.json())
    .then(info => console.log(info))
    .catch(err => console.error(`error ${err}`));
}
