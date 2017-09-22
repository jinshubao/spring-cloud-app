import {
    ADD_BUTTON_LOADING,
    ADD_DIALOG_OPEN_FLAG,
    COMMON_PARAMS,
    CUSTOMER_PARAMS,
    EDIT_BUTTON_LOADING,
    EDIT_DIALOG_OPEN_FLAG,
    LIST_LOADING,
    RESULT_DATA
} from './constant'

export default {
    [RESULT_DATA]: {},
    [LIST_LOADING]: false,
    [ADD_DIALOG_OPEN_FLAG]: false,
    [EDIT_DIALOG_OPEN_FLAG]: false,
    [ADD_BUTTON_LOADING]: false,
    [EDIT_BUTTON_LOADING]: false,
    [COMMON_PARAMS]: {
        keyword: '',
        page: 1,
        size: 20
    },
    [CUSTOMER_PARAMS]: {}
}