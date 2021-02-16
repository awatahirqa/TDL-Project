'use strict';

const ListName = document.querySelector("#ListName");
const tasklist = document.querySelector("#tasks");

const ListNameRead = document.querySelector("#ListNameRead");

const ListIdUpdate = document.querySelector("#ListIdUpdate");
const ListNameUpdate = document.querySelector("#ListNameUpdate");

const ListIdDelete = document.querySelector("#ListNameDelete");

const createToDoList = () => {
    const ListNameValue = ListName.value;

    let data = {
        "tasklist": [null],
        "name": ListNameValue
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
    const ListNameReadValue = ListNameRead.value;

    fetch(`http://localhost:8080/ToDoList/read/${ListNameReadValue}`)
        .then(response => response.json())
        .then(info => console.log(info))
        .catch(err => console.error(`error ${err}`));
}


const updateList = () => {
    const ListIdUpdateValue = ListIdUpdate.value;
    const ListNameUpdateValue = ListNameUpdate.value;

    let data = {
        
        "tasklist": null,
        "name": ListNameUpdateValue
    }

    fetch(`http://localhost:8080/ToDoList/replace/${ListIdUpdateValue}`, {
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
    const ListIdDeleteValue = ListIdDelete.value;

    fetch(`http://localhost:8080/ToDoList/delete/${ListIdDeleteValue}`, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json"
        }
    })
    .then(response => response.json())
    .then(info => console.log(info))
    .catch(err => console.error(`error ${err}`));
}
