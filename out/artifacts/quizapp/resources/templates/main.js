//Vue JS app

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
    template: `<div class="one-item">
                <label>
                <input type="radio" name="item.name" :value="item.id" @change="$emit('pickedoption', item)">
                <span v-text="item.name"></span>
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
            errors: []
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
                        '<h3 style="color: #8c8c8c;"><span style="color: red">{{ quiz.name }}</span>  with <span style="color: #0FA0CE">{{ quiz.qnums }} </span>questions </h3>' +
                        '<question-list :questions="questions" :key="questions.id" @out="pickchoicesadd($event)"/>' +
                        '<button @click="sendresult">Закончить опрос</button>' +
                    '</div></div>'+
                '</div>'
});

//Question list component
Vue.component('question-list', {
    props: ['questions'],
    data: function() { return { pickchoice: '', pickchoices: [] }},
    methods: {
        pickchoicesadd(n) {
            // console.log(n)
            this.pickchoices[n.question] = n.choice;
            // console.log("QUESTION-LIST: pickchoices[" + n.question + "] = " + this.pickchoices[n.question]);
            this.$emit('out',this.pickchoices)
        }
    },
    template: `<ol><li v-for="q_one in questions">
                        <span style="font-size: large;">{{ q_one.text }}</span>
                        <question-single v-bind:q="q_one" @pickedoption="pickchoicesadd($event)"></question-single>
                    </li></ol>`
});


//Show one question
Vue.component('question-single', {
    props: ['q'],
    data: function () {
        return { picked: ''}
    },
    template: `
            <div class="question-one">
            <form action="/quiz" method="post">
            <input type="hidden" name="sessionid" value="sessionid"/>
            <label v-for="option in q.options">
            <input type="radio" name="option" :value="option.text" @change="$emit('pickedoption', { 'question': q.id, 'choice': option.id })" v-model="picked">
                <span v-text="option.text"></span>
            </input>
            </label>
            <br><span> Selected answer: {{ picked }} </span>
            </form>
            </div>
            `
});



var quizroot = new Vue({
    el: '#quizroot',
})