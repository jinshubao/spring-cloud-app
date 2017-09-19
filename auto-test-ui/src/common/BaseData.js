import {
    ADD_BUTTON_LOADING,
    ADD_DIALOG_OPEN_FLAG,
    ADD_FORM_DATA,
    ADD_FORM_RULES,
    EDIT_BUTTON_LOADING,
    EDIT_DIALOG_OPEN_FLAG,
    EDIT_FORM_DATA,
    EDIT_FORM_RULES,
    LIST_LOADING,
    QUERY_PARAMS,
    RESULT_DATA
} from './constant'

export default {

    [RESULT_DATA]: {},
    [LIST_LOADING]: false,
    [ADD_DIALOG_OPEN_FLAG]: false,
    [EDIT_DIALOG_OPEN_FLAG]: false,
    [ADD_BUTTON_LOADING]: false,
    [EDIT_BUTTON_LOADING]: false,
    [ADD_FORM_DATA]: {},
    [EDIT_FORM_DATA]: {},
    [QUERY_PARAMS]: {
        keyword: '',
        page: 1,
        size: 20
    },
    [ADD_FORM_RULES]: {},
    [EDIT_FORM_RULES]: {}
}