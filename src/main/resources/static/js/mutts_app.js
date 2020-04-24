//let userID = +"3";
let userID = document.getElementById("user_id").value;
// let baseUrl = 'http://demo.codingnomads.co:8082/muttsapp/users';
let baseUrl = '/users';

const createChatBubble = (msg) => {
    let chatBubble = document.createElement('div');
    console.log(msg.senderId);
    console.log(userID);
    if(msg.senderId === +userID){
        chatBubble.classList.add("chat-bubble", "out")
    } else {
        chatBubble.classList.add("chat-bubble", "in")
    }
    let paragraph = document.createElement('p');
    paragraph.innerText = msg.message;
    chatBubble.appendChild(paragraph);
    let wrapper = document.getElementById('chat-bubble-wrapper');
    wrapper.prepend(chatBubble)
}

// sender Id matches userId give it one style in, if not give it other style out

function createChatBubbles(dataObj) {
    document.getElementById('chat-bubble-wrapper').innerHTML=" ";
    let messageArr = dataObj.data;
    messageArr.forEach(chat => createChatBubble(chat))
}

function getUserChats() {
    document.getElementById('message-wrapper').innerHTML = " ";
    fetch(baseUrl + "/" + userID + "/chats/")
        .then(response => response.json())
        .then(dataObj => {
        console.log(dataObj)
        createPreviewBoxes(dataObj)})
};

getUserChats();

function createPreviewBox(chat) {
    console.log(chat);
    let previewBox = document.createElement('div');
    previewBox.classList.add('message-preview-box');
    previewBox.setAttribute('data-chat_id', chat.chatId)
    previewBox.setAttribute('data-sender_id', chat.senderId)
    previewBox.addEventListener('click', previewBoxClick)

    let imageWrap = document.createElement('div');
    imageWrap.setAttribute('data-chat_id', chat.chatId)
    imageWrap.setAttribute('data-sender_id', chat.senderId)
    imageWrap.classList.add('img-wrap');

    let image = document.createElement('img');
    image.setAttribute('data-chat_id', chat.chatId)
    image.setAttribute('data-sender_id', chat.senderId)
    image.setAttribute('src', chat.photoUrl)
    image.setAttribute('alt', 'default icon')
    imageWrap.appendChild(image);
    previewBox.appendChild(imageWrap);

    let textWrap = document.createElement('div');
    textWrap.setAttribute('data-chat_id', chat.chatId);
    textWrap.setAttribute('data-sender_id', chat.senderId)
    textWrap.classList.add('message-text-wrap');    

    let p1 = document.createElement('p');
    p1.setAttribute('data-chat_id', chat.chatId);
    p1.setAttribute('data-sender_id', chat.senderId)
    p1.innerHTML = chat.chatName;

    let p2 = document.createElement('p');
    p2.setAttribute('data-chat_id', chat.chatId);
    p2.setAttribute('data-sender_id', chat.senderId)
    console.log(chat);
    p2.innerHTML = chat.message;
    textWrap.appendChild(p1);
    textWrap.appendChild(p2);
    previewBox.appendChild(textWrap);

    let dateWrap = document.createElement('div');
    dateWrap.setAttribute('data-chat_id', chat.chatId);
    dateWrap.setAttribute('data-sender_id', chat.senderId);
    dateWrap.classList.add("date-wrap");

    let dateP = document.createElement('p');
    dateP.setAttribute('data-chat_id', chat.chatId);
    dateP.setAttribute('data-sender_id', chat.senderId);
    dateP.innerHTML = new Date(chat.dateSent).toLocaleDateString();
    dateWrap.appendChild(dateP)
    previewBox.appendChild(dateWrap);

    let messageWrap = document.getElementById("message-wrapper")
    messageWrap.appendChild(previewBox);
 }

function createPreviewBoxes(dataObj){
    let chatsArr = dataObj.data;
    chatsArr.forEach(chat => createPreviewBox(chat))
} 


function previewBoxClick(event) {
    document.getElementById('new-message').removeAttribute('disabled');
    document.getElementById("chat-bubble-wrapper").innerHTML = " ";
    let chatID = event.target.dataset.chat_id;
    let senderID = event.target.dataset.sender_id;
    
    document.getElementById('send-message').dataset.chatId = chatID;
    fetch(baseUrl + "/" + userID + '/chats/' + senderID)
        .then(responsse => responsse.json())
        .then(dataObj => {
            console.log(dataObj)
            createChatBubbles(dataObj)
        })

    fetch(baseUrl + "/" + senderID)
        .then(response => response.json())
        .then(dataObj => {
            document.getElementById('recipient').setAttribute('src', dataObj.data.photoUrl);
            document.querySelector('#header-number > p').innerHTML = dataObj.data.firstName + " " + dataObj.data.lastName;
        })
    }

let sendMessage = document.getElementById('send-message');
sendMessage.addEventListener('submit', function(event){
    event.preventDefault();
    let msg = document.getElementById('new-message').value;
    let messageObj = {
        message:msg,
        senderID:userID,
        chatID:+event.target.dataset.chatId,
        otherUserID:+event.target.dataset.senderId,
    } 
    createChatBubble(messageObj);
    sendNewMessage(messageObj);
    document.getElementById("new-message").value = " ";
});

function sendNewMessage(msgObj) {
    console.log(msgObj)
    let postParams = {
       method: 'POST', // *GET, POST, PUT, DELETE, etc.
       headers: {
            'Content-Type': 'application/json; charset=UTF-8',
            "Access-Control-Allow-Headers": "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With",
            "Access-Control-Allow-Origin": "*"
        },
       body: JSON.stringify(msgObj)
    };
    fetch(`${baseUrl}/${userID}/chats/${otherUserID}`, postParams)
        .then(res => res.json())
        .then(res => {
            console.log(res)
            return getUserChats();
        });
}

let newChatBtn = document.getElementById('new-chat-btn');
let newChatModalBody = document.getElementById('new-chat-modal-body');
newChatBtn.addEventListener('click', makeNewChatForm);

function makeNewChatForm(){
    fetch(`${baseUrl}/users/`)
        .then(res => res.json())
        .then(data => {
            console.log(data)
            let usersArray = data.data;

            let form = document.createElement('form');
            form.id = 'new-chat-form';

            let formString = ``;
            formString +=  '<select name="user">'
            usersArray.forEach(userObj => {
                formString += `<option value="${userObj.id}">${userObj.first_name} ${userObj.last_name}</option> `
            })

            formString += `</select>`
            formString += `<input type="submit" class="btn btn-success">`
            form.innerHTML = formString;
            form.addEventListener('submit', newChatSubmit)
            newChatModalBody.innerHTML = "";
            newChatModalBody.appendChild(form);
        })
}

function newChatSubmit(e){
    e.preventDefault();

    let newChatUser = document.querySelector('select[name="user"]');
    let newChatUserId = newChatUser.value;
    let newChatUserName = newChatUser.querySelector('option:checked').innerHTML;

    console.log({ chat_title: newChatUserName })
    let postParams = {
        method: 'POST', // *GET, POST, PUT, DELETE, etc.
        headers: {
            'Content-Type': 'application/json; charset=UTF-8',
            "Access-Control-Allow-Headers": "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With",
            "Access-Control-Allow-Origin": "*"
        },
        body: JSON.stringify({chat_title: newChatUserName})
    }
    fetch(`${baseUrl}/users/` + userID+ "/chats/" + newChatUserId , postParams)
    .then(res => res.json())
    .then(res => {
        console.log(res)
        createPreviewBox(res.data, false)
    })
}

function makeNewChatForm(e) {
    newChatModalBody.innerHTML = "Loading Chat Form";
    fetch(`${baseUrl}/users/`)
        .then(res => res.json())
        .then(data => {
            console.log(data);
            let usersArray = data.data;
            let frm = document.createElement('form');
            frm.id = `new-chat-frm`;
            let formString = ``;
            formString += `<input id="new-chat-user" type="text" list="users-list" class="form-control">`;
            formString += `<datalist id="users-list">`
            usersArray.forEach(userObj => {
                formString += `<option data-value="${userObj.id}" value="${userObj.first_name} ${userObj.last_name}"></option> `
            })
            formString += `</datalist>`
            formString += `<input type="submit" class="btn btn-success">`
            frm.innerHTML = formString;
            frm.addEventListener('submit', newChatSubmit)
            newChatModalBody.innerHTML = "";
            newChatModalBody.appendChild(frm);
        })
}
function newChatSubmit(e){
    e.preventDefault()
    let options = document.getElementById('users-list').options;
    console.log(document.getElementById('users-list').options)
    console.log(e.target.elements)
    let val = e.target.elements["new-chat-user"].value
    console.log(val)
    let newChatUserId;
    Array.from(options).forEach(option => {
        if (option.value === val) {
            newChatUserId = option.getAttribute('data-value');
        }
    })
    console.log(newChatUserId) 
    // Write submit fetch here
}


