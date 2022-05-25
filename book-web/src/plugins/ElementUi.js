import Vue from "vue";
import {
    Button,
    Form,
    FormItem,
    Input,
    Checkbox,
    Message,
    Container,
    Aside,
    Header,
    Main,
    Footer,
    Row,
    Col,
    Menu,
    MenuItemGroup,
    MenuItem,
    Submenu,
} from "element-ui";

Vue.use(Button);
Vue.use(Form);
Vue.use(FormItem);
Vue.use(Input);
Vue.use(Checkbox);
Vue.use(Container);
Vue.use(Aside);
Vue.use(Header);
Vue.use(Main);
Vue.use(Footer);

Vue.use(Row);
Vue.use(Col);
Vue.use(Menu);
Vue.use(Submenu);
Vue.use(MenuItemGroup);
Vue.use(MenuItem);

Vue.prototype.$message = Message;