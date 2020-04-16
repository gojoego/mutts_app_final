let userID = +"3";
let baseUrl = 'http://demo.codingnomads.co:8082/muttsapp/users';

const createChatBubble = (msg) => {
    let chatBubble = document.createElement('div');
    if(msg.sender_id === userID){
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


function createChatBubbles(dataObj) {
    document.getElementById('chat-bubble-wrapper').innerHTML=" ";
    let messageArr = dataObj.data;
    messageArr.forEach(chat => createChatBubble(chat))
}

function getUserChats() {
    document.getElementById('message-wrapper').innerHTML = " ";
    fetch(baseUrl + "/" + userID + "/chats/")
        .then(response => response.json())
        .then(dataObj => createPreviewBoxes(dataObj))
};

getUserChats();

function createPreviewBox(chat) {
    let previewBox = document.createElement('div');
    previewBox.classList.add('message-preview-box');
    previewBox.setAttribute('data-chat_id', chat.chat_id)
    previewBox.setAttribute('data-sender_id', chat.sender_id)
    previewBox.addEventListener('click', previewBoxClick)

    let imageWrap = document.createElement('div');
    imageWrap.setAttribute('data-chat_id', chat.chat_id)
    imageWrap.setAttribute('data-sender_id', chat.sender_id)
    imageWrap.classList.add('img-wrap');

    let image = document.createElement('img');
    image.setAttribute('data-chat_id', chat.chat_id)
    image.setAttribute('data-sender_id', chat.sender_id)
    image.setAttribute('src', chat.photo_url)
    image.setAttribute('alt', 'default icon')
    imageWrap.appendChild(image);
    previewBox.appendChild(imageWrap);

    let textWrap = document.createElement('div');
    textWrap.setAttribute('data-chat_id', chat.chat_id);
    textWrap.setAttribute('data-sender_id', chat.sender_id)
    textWrap.classList.add('message-text-wrap');    

    let p1 = document.createElement('p');
    p1.setAttribute('data-chat_id', chat.chat_id);
    p1.setAttribute('data-sender_id', chat.sender_id)
    p1.innerHTML = chat.chat_name;

    let p2 = document.createElement('p');
    p2.setAttribute('data-chat_id', chat.chat_id);
    p2.setAttribute('data-sender_id', chat.sender_id)
    p2.innerHTML = chat.last_message;
    textWrap.appendChild(p1);
    textWrap.appendChild(p2);
    previewBox.appendChild(textWrap);

    let dateWrap = document.createElement('div');
    dateWrap.setAttribute('data-chat_id', chat.chat_id);
    dateWrap.setAttribute('data-sender_id', chat.sender_id);
    dateWrap.classList.add("date-wrap");

    let dateP = document.createElement('p');
    dateP.setAttribute('data-chat_id', chat.chat_id);
    dateP.setAttribute('data-sender_id', chat.sender_id);
    dateP.innerHTML = new Date(chat.date_sent).toLocaleDateString();
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
    console.log(event.target.dataset)
    let chatID = event.target.dataset.chat_id;
    let senderID = event.target.dataset.sender_id;
    
    document.getElementById('send-message').dataset.chat_id = chatID;
    fetch(baseUrl + "/" + userID + '/chats/' + senderID)
        .then(responsse => responsse.json())
        .then(dataObj => createChatBubbles(dataObj))

    fetch(baseUrl + "/" + senderID)
        .then(response => response.json())
        .then(dataObj => {
            console.log(dataObj)
            document.getElementById('recipient').setAttribute('src', dataObj.data.photo_url);
            document.querySelector('#header-number > p').innerHTML = dataObj.data.first_name + " " + dataObj.data.last_name;
        })
    }

let sendMessage = document.getElementById('send-message');
sendMessage.addEventListener('submit', function(event){
    console.log(event)
    event.preventDefault();
    let msg = document.getElementById('new-message').value;
    let messageObj = {
        message:msg,
        sender_id:userID,
        chat_id:+event.target.dataset.chat_id,
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
    fetch(`${baseUrl}/${userID}/chat/`, postParams)
        .then(res => res.json())
        .then(res => {
            console.log(res)
            return getUserChats();
        });
}

function createNewChat (){

}

// function newUser() {
//     let postData = {
//         first_name: "",
//         last_name: "",
//         username: "",
//         photo_url: ""
//     }
//     let postParams = {
//        method: 'POST', // *GET, POST, PUT, DELETE, etc.
//        headers: {
//            "Content-Type": "application/json; charset=UTF-8"
//        },
//        body: JSON.stringify(postData)
//     }
//     fetch('http://demo.codingnomads.co:8080/muttsapp/users/', postParams)
//         .then(res => res.json())
//         .then(res => console.log(res))
// };

// (function getUsers(){
//     fetch('http://demo.codingnomads.co:8080/muttsapp/users/')
//         .then(response => { 
//             return response.json()
//         })
//         .then( dataObj => {
//             console.log(dataObj)
//             let chatsArr = dataObj.data;
//             chatsArr.forEach( (chat) => {
//                 createPreviewBox(chat)
//             })
//         })
//     })();


// let chats = [
//   {
//     sender_id: "1",
//     photo_url: "./images/icons8-bullbasaur-50.png",
//     last_message: "hi",
//     chat_name: "Bullbasaur",
//     date_sent: "3/19/20"
//   },
//   {
//     sender_id: "2",
//     photo_url: "./images/icons8-pikachu-pokemon-50.png",
//     last_message: "hello",
//     chat_name: "pikachu",
//     date_sent: "3/19/20"
//   },
//   {
//     sender_id: "3",
//     photo_url: "./images/icons8-charmander-50.png",
//     last_message: "How it's going?",
//     chat_name: "Charmander",
//     date_sent: "3/20/20"
//   },
//   {
//     sender_id: "4",
//     photo_url: "./images/icons8-eevee-50.png",
//     last_message: "Hey girl!",
//     chat_name: "Eevee",
//     date_sent: "3/21/20"
//   },
//   {
//     sender_id: "5",
//     photo_url: "./images/icons8-jigglypuff-50.png",
//     last_message: "Wanna hangout?",
//     chat_name: "Jiggly Puff",
//     date_sent: "3/22/20"
//   },
//   {
//     sender_id: "6",
//     photo_url: "./images/icons8-dratini-50.png",
//     last_message: "See ya later!",
//     chat_name: "Dratini",
//     date_sent: "3/23/20"
//   }
// ];
