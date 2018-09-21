//Vue JS app

var imagepath = '/resources/images/';

// register modal component
// https://ru.vuejs.org/v2/examples/modal.html
Vue.component('modal-win', {
    props: ['imagethis'],
    template: `            
              <transition name="modal">
                <div class="modal-mask">
                  <div class="modal-wrapper">
                    <div class="modal-container">
                    
                    <!--https://www.w3schools.com/howto/tryit.asp?filename=tryhow_css_js_lightbox-->
                    
            <span class="close cursor" @click="$emit('close')">&times;</span>
                      <div class="modal-header">
                        <slot name="header">
                          
                        </slot>
                      </div>
            
                      <div class="modal-body">
                      <img :src="imagethis">
                      <br>
                        <slot name="body">
                          
                        </slot>
                      </div>
            
                      <div class="modal-footer">
                        <slot name="footer">
                        DVFU 2018
                        </slot>
                        <button class="modal-default-button" @click="$emit('close')">
                            OK
                          </button>
                      </div>
                    </div>
                  </div>
                </div>
              </transition>
    `
});


// Component List Quiz (show name quizs)
Vue.component('quiz-list',{
    data: function () {
        return {
            quizlist: [],
            endpoint: '/rest/quiz/list',
            picked: '',
            ok: false
        }
    },
    mounted(){
        this.fetchQuizList();
    },
    methods: {
        fetchQuizList(){
            axios
                .get(this.endpoint)
                .then(r => this.quizlist = r.data);
        },
        showitem(item){
            // console.log(item.id);
            this.picked = item;
            this.ok = item.qnums > 0 ? true : false;
        }
    },
    template: `<div><ol>
                <choose-item v-for="item in quizlist" :item="item" :key="item.id" @pickedoption="showitem($event)"></choose-item>
                </ol>
                <!--<span>choosed: {{ picked.qnums }}</span>-->
                <br>
                <quiz-selected v-show="ok" :quizPicked='picked'><span class="fa fa-user"></span>Choosed test: </quiz-selected>
                </div>`
});

// One item of Quiz list
Vue.component('choose-item',{
    props: ['item'],
    data: function(){
        return {
            imagepath: window.imagepath,
            showModal: false
        };
    },
    template: `<div class="one-item">
                <label>
                <input type="radio" name="item.name" :value="item.id" @change="$emit('pickedoption', item)">
                <span v-text="item.name"></span>&nbsp;&nbsp;
                <span class="tooltip" v-if="(item.picture !== '')">
                    <img :src="imagepath + 'thumb/quiz/' + item.id + '/' + item.picture" width="20">
                    <img class="tooltiptext" :src="imagepath + 'thumb/quiz/' + item.id + '/' + item.picture">
                </span>
                </input>
                </label>
                </div>
                `
})

///////////////////////////////////////////////////////////////////////////////

// List Questions with Options!!!
Vue.component('quiz-selected',{
    props: ['quizPicked'],
    data: function () {
        return {
            questions: [],
            answers: [],
            answersend: [],
            pickchoices: [],
            quiz: 0,
            urlget: '/rest/quiz/',
            urlpostresult: '/rest/quiz/',
            errors: [],
            imagepath: window.imagepath,
            imagetest: window.imagepath + 'thumb/quiz/20/Java_programming_language_logo.svg.png',
            img: '',
            showModal: false
        }
    },
    watch: {
        quizPicked: function (newVal, oldVal) {
            // console.log("newVal" + newVal)
            this.quiz = newVal;
            this.getQuestions(newVal.id);
        }
    },
    methods: {
        getQuestions(id){
            axios
                .get(this.urlget + id)
                .then(function(res){
                        this.questions = res.data;
                    }
                        .bind(this)
                );
        },
        postAnswers(bodytosend){
            axios
                .post(this.urlpostresult,{ body: bodytosend })
                .then( function (r) {
                    console.log(r);
                })
                .catch(function (e) {
                    console.log(e);
                })
        },
        sendresult (){

            // prepare data array ->
            this.pickchoices.forEach((el, i, x) => i? this.answers.push({'q': i, 'c': el}) : null)

            // console.log(this.answers)
            // console.log("this.answers.length: " + this.answers.length)
            // console.log(this.answersend)
            // console.log(this.pickchoices)

            // create json object
            this.answersend = JSON.stringify(this.answers);
            console.log("Sending result to server:")
            this.postAnswers(this.answersend)

        },
        pickchoicesadd(n){
            // console.log(n)
            this.pickchoices = n;

        }
    },
    template:   '<div class="container">' +
                    '<div class="row">' +
                        '<slot></slot>' +
                        '<div class="twelve columns">' +
                        '<h3 style="color: #8c8c8c;">' +
                            '<span class="tooltip" v-if="(quiz.picture !== null)">' +
                                '<img @click="showModal = true" :src="imagepath + \'thumb/quiz/\' + quiz.id + \'/\' + quiz.picture" width="20">' +
                                '<img class="tooltiptext" style="background: whitesmoke;" :src="imagepath + \'thumb/quiz/\' + quiz.id + \'/\' + quiz.picture">' +
                            '</span>&nbsp;&nbsp;' +
                            '<span style="color: red">{{ quiz.name }}</span>  with <span style="color: #0FA0CE">{{ quiz.qnums }} </span>questions ' +
                        '</h3>' +

                        '<modal-win :imagethis="imagetest" v-if="showModal" @close="showModal = false">' +
                            '<h3 slot="header">{{ quiz.name }}</h3>' +
                            '<h4 slot="body">with <span style="color: #0FA0CE">{{ quiz.qnums }} </span> questions </h4>' +
                        '</modal-win>' +

                        '<question-list :questions="questions" :key="questions.id" :quizid="quiz.id" @out="pickchoicesadd($event)"/>' +
                        '<button @click="sendresult">Закончить опрос</button>' +
                    '</div></div>'+
                '</div>'
});

//Question list component
Vue.component('question-list', {
    props: ['questions','quizid'],
    data: function() { return { pickchoice: '', pickchoices: [], showModal: false }},
    methods: {
        pickchoicesadd(n) {
            // console.log(n)
            this.pickchoices[n.question] = n.choice;
            // console.log("QUESTION-LIST: pickchoices[" + n.question + "] = " + this.pickchoices[n.question]);
            this.$emit('out',this.pickchoices)
        }
    },
    template: `<ol><li v-for="q_one in questions"> 
                        <question-single :q="q_one" :quizid="quizid" @pickedoption="pickchoicesadd($event)"></question-single>
                    </li></ol>`
});


//Show one question
Vue.component('question-single', {
    props: ['q', 'quizid'],
    data: function () {
        return { picked: '',
            imagepath: window.imagepath,
            showModal: false,
            imagethis: imagepath + 'quiz/' + this.$props.quizid + '/questions/' + this.$props.q.id + '/' + this.$props.q.picture
        }
    },
    template: `
            <span class="question-one">
            <span class="tooltip">
                <span style="font-size: large;">{{ q.text }}</span>
                    &nbsp;&nbsp;
                    <span v-if="(q.picture !== '' )">
                        <img :src="imagepath + 'thumb/quiz/' + quizid + '/questions/' + q.id + '/' + q.picture" width="50" @click="showModal = true">
                        <img class="tooltiptext" style="background: whitesmoke;" :src="imagepath + 'quiz/' + quizid + '/questions/' + q.id + '/' + q.picture">
                    </span>
                </span>
                       <br><br>

                <modal-win :imagethis="imagethis" v-if="showModal" @close="showModal = false">
                    <h3 slot="header">{{ q.text }}</h3>
                    <div slot="body">
                    <label v-for="option in q.options">
                    <input type="radio" name="option" :value="option.text" @change="$emit('pickedoption', { 'question': q.id, 'choice': option.id })" v-model="picked">
                        <span v-text="option.text"></span>
                    </input>
                    </label>
                    </div>
                    <span slot="footer"> Selected answer: {{ picked }} </span>
                </modal-win>

                <form action="/quiz" method="post">
                    <input type="hidden" name="sessionid" value="sessionid"/>
                    <label v-for="option in q.options">
                    <input type="radio" name="option" :value="option.text" @change="$emit('pickedoption', { 'question': q.id, 'choice': option.id })" v-model="picked">
                        <span v-text="option.text"></span>
                    </input>
                    </label>
                        <span> Selected answer: {{ picked }} </span>
                </form>
            </span>
            `
});



var quizroot = new Vue({
    el: '#quizroot',
})
