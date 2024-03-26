const $ = document.querySelector.bind(document);


function Validator (option) {
    const formElement = $(option.form);

    // Obj luu tat ca cac rule
    const selectorRules = {};

    // Lay ra elemnt la form group cha chua input xu ly
    function getParent(element, selector) {
        while(element.parentElement) {
            if(element.parentElement.matches(selector)) {
                return element.parentElement;
            }
            element = element.parentElement;
        }
    };

    function validate(inputElement, rule) {

        // Lay ra tat ca cac rule cua element can validate
        const rules = selectorRules[rule.selector]
        // Lap qua tung rule cua element & kiem tra, neu co loi dung kiem tra
        for (const rule of rules) {
            var errorMessage = rule(inputElement.value)
            if (errorMessage) {
                showError(inputElement, errorMessage);
                break;
            } else {
                clearError(inputElement);
            }
        }
        return !errorMessage;
    }

    // Hien thi khi co loi
    function showError(inputElement, errorMessage) {
        const parent = getParent(inputElement, option.formGroupSelector);
        parent.classList.add('invalid');
        parent.querySelector(option.errorSelector).innerText = errorMessage;
    };
    // Tat hien thi khi loi
    function clearError(inputElement) {
        const parent = getParent(inputElement, option.formGroupSelector)
        parent.classList.remove('invalid');
        parent.querySelector(option.errorSelector).innerText = '';
    };

    if (formElement) {

        //Loai bo mac dinh cua form khi submit
        formElement.onsubmit = function(e) {
            e.preventDefault();

            var valid = [];
            var checkValid = false;

            option.rules.forEach(function(rule) {
                const inputElement = formElement.querySelector(rule.selector);
                var formValid = validate(inputElement, rule);
                valid.push(formValid);
            })
            checkValid = valid.every(function(value) {
                return value;
            })
            if (checkValid) {
                if (typeof option.onsubmit === 'function') {
                    var enbleInput = formElement.querySelectorAll('[name]')
                    var dataSubmit = Array.from(enbleInput).reduce(function(obj, input){
                        obj[input.name] = input.value;
                        return obj;
                    },{})
                    option.onsubmit(dataSubmit);
                    formElement.submit();
                } else {

                }
            } else {
                console.log('Co loi xay ra khong the Submit!')
            }
        }

        option.rules.forEach((rule) => {

            if (Array.isArray(selectorRules[rule.selector])) {
                selectorRules[rule.selector].push(rule.test)
            } else {
                selectorRules[rule.selector] = [rule.test];
            };
            
            const inputElement = formElement.querySelector(rule.selector)
            
            inputElement.onblur = function() {
                validate(inputElement, rule);
            }
            inputElement.oninput = function() {
                clearError(inputElement);
            }
        });
    };
};


Validator.isRequired = function (selector) {
    return {
        selector,
        test(value) {
            return value ? undefined : 'Vui long nhap truong nay';
        }
    };
};

Validator.isEmail = function (selector) {
    return {
        selector,
        test(value) {
            const regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
            return regex.test(value) ? undefined : 'Vui long nhap dung dinh dang email';
        }
    };
};
Validator.minLength = function(selector, minValue) {
    return {
        selector,
        test(value) {
            return value.length >= minValue ? undefined : `Vui long nhap toi thieu ${minValue} ky tu`
        }
    };
}
Validator.isConfirmed = function(selector, getConfirm) {
    return {
        selector,
        test(value) {
            return value === getConfirm() ? undefined : 'Du lieu nhap lai khong chinh xac'
        }
    };
}