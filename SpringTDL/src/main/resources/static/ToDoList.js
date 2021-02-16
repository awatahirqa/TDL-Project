'use strict';

const ListName = document.querySelector("#ListName");
const tasklist = document.querySelector("#tasks");

const ListNameRead = document.querySelector("#ListNameRead");

const ListIdUpdate = document.querySelector("#ListIdUpdate");
const ListNameUpdate = document.querySelector("#ListNameUpdate");

const ListIdDelete = document.querySelector("#ListNameDelete");

const Createconfirm = document.querySelector('#createConfirmed');
const readById1 = document.querySelector('#readbyid');
const Updateconfirm = document.querySelector('#updateConfirmed');
const readbyid = document.querySelector('#readByID');
const taskprint = document.querySelector('#readall')
const Deleteconfirm = document.querySelector('#DeleteConfirmed');

const printReadToScreen = (read) => {
	let user = document.createElement("p");
	let text = document.createTextNode(`${read}`);
	user.appendChild(text);
	readbyid.appendChild(user);

}

const printToDoListToScreen = (List) => {
	let user = document.createElement("p");
	let text = document.createTextNode(`${List}`);
	user.appendChild(text);
	taskprint.appendChild(user);

}

const printDeleteToScreen = (deleted) => {
	let user = document.createElement("p");
	let text = document.createTextNode(`your task with id ${deleted} was deleted`);
	user.appendChild(text);
	Deleteconfirm.appendChild(user);
}



const printCreateConfirm = (created) => {
	let user = document.createElement("p");
	let text = document.createTextNode(`${created}`);
	user.appendChild(text);
	Createconfirm.appendChild(user);


}
const printUpdateConfirm = (updated) => {
	let user = document.createElement("p");
	let text = document.createTextNode(`${updated}`);
	user.appendChild(text);
	Updateconfirm.appendChild(user);


}


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
    .then(printCreateConfirm (`your List ${ListNameValue} was created`))
    .catch(err => console.error(`error ${err}`));
}

const readAllToDoLists = () => {
    fetch("http://localhost:8080/ToDoList/readAll")
        .then(response => response.json())
        .then(info => {
            for (let ToDoList of info) {
                console.log(ToDoList);
                printToDoListToScreen(ToDoList.list_Id + " | " + ToDoList.name);
            }
        })
        .catch(err => console.error(`error ${err}`));
}

const readByListName = () => {
    const ListNameReadValue = ListNameRead.value;

    fetch(`http://localhost:8080/ToDoList/read/${ListNameReadValue}`)
    .then((response) => {
        // check that the response is OK (i.e. 200)
        if(response.status !== 200){
            throw new Error("I don't have a status of 200");
        }else{
            // console.log(response);
            // console.log(`response is OK (200)`);
            //json-ify it (which returns a promise)
            response.json().then((infofromserver) =>{
                console.log(infofromserver);
                let myJSON = JSON.stringify(infofromserver)
					console.table(infofromserver);
					printReadToScreen(myJSON);
            })
        }
    }).catch((err) => {
        console.error(err);
    })
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
    .then(printUpdateConfirm(`your todolist ${ListIdUpdateValue} was updated)`))
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
    .then(printDeleteToScreen(`your List with id ${ListIdDeleteValue} `))
    .catch(err => console.error(`error ${err}`));
}
