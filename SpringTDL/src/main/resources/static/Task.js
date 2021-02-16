'use strict';

const summary = document.querySelector("#summary");
const priority = document.querySelector("#priority");
const deadline = document.querySelector("#deadline");
const mylist = document.querySelector("#myList");
const status = document.querySelector("#status");
const TaskIdRead = document.querySelector("#TaskIdRead");
const TaskIdUpdate = document.querySelector("#TaskIdUpdate");
const Summary_update = document.querySelector("#SummaryUpdate");
const priority_update = document.querySelector("#PriorityUpdate");
const deadline_udate = document.querySelector("#DeadlineUpdate");
const mylist_update = document.querySelector("#MyListIDUpdate");
const status_update = document.querySelector("#StatusUpdate");
const TaskIdDelete = document.querySelector("#TaskIdDelete");
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

const printTaskToScreen = (tasks) => {
	let user = document.createElement("p");
	let text = document.createTextNode(`${tasks}`);
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

const createTask = () => {
    const summaryValue = summary.value; 
    const priorityValue = priority.value;
    const deadlineValue = deadline.value;
    const mylistValue = mylist.value;
    const statusValue = status.value;

    let data = {
        "summary": summaryValue,
        "priority": priorityValue, 
        "deadline" : deadlineValue,
        "mylist" :  {"id" : mylistValue},
        "status" :  statusValue
    }
    console.log(data);
    console.log(JSON.stringify(data));
    fetch("http://localhost:8080/Tasks/create",{
        method: "POST", 
        body: JSON.stringify(data),
        headers:{
            "Content-Type":"application/json"
        }
    })
    .then(response => response.json())
    .then(info => console.log(info))
    .then(printCreateConfirm (`your task ${summaryValue} was created`))
    .catch(err => console.error(`Something went wrong! ${err}`));
}

const tasksreadAll = () => {
    fetch("http://localhost:8080/Tasks/readAll")
    .then(response => response.json())
    .then(info => {
        for (let Task of info) {
            console.log(Task);
            printTaskToScreen(Task.Id + " | " + Task.summary + " | " + Task.status);
        }
    })
    .catch(err => console.error(`error ${err}`));
}

const tasksreadOne = (id) => {
    const TaskIdReadValue = TaskIdRead.value;

    fetch(`http://localhost:8080/Tasks/read/${TaskIdReadValue}`)
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

const updateTask = () => {
const   TaskIdUpdateValue = TaskIdUpdate.value;
const summaryValue = Summary_update.value; 
const priorityValue = priority_update.value;
const deadlineValue = deadline_udate.value;
const mylistValue = mylist_update.value;
const statusValue = status_update.value;
 
 
    let data = {
        "summary": summaryValue,
        "priority": priorityValue, 
        "deadline" : deadlineValue,
        "mylist" :  {"id" : mylistValue},
        "status" :  statusValue
    }
 
    fetch(`http://localhost:8080/Tasks/replace/${TaskIdUpdateValue}`, {
        method: "PUT", 
        body: JSON.stringify(data),
        headers:{
            "Content-Type":"application/json"
        }
    })
    .then(response => response.json())
    .then(info => console.log(info))
    .then(printUpdateConfirm(`your task ${TaskIdUpdateValue} was updated)`))
    .catch(err => console.error(`Something went wrong! ${err}`));
}

const deleteTask = () => {
    const TaskIdDeleteValue = TaskIdDelete.value;
 
    fetch(`http://localhost:8080/Tasks/delete/${TaskIdDeleteValue}`, {
        method: "DELETE", 
        headers:{
            "Content-Type":"application/json"
        }
    })
    .then(response => response.json())
    .then(info => console.log(info))
    .then(printDeleteToScreen(`your task with id ${TaskIdDeleteValue} `))
    .catch(err => console.error(`Something went wrong! ${err}`));
}
