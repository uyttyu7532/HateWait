package com.example.hatewait.register

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hatewait.R
import com.example.hatewait.socket.NonMemberRegisterAsyncTask
import kotlinx.android.synthetic.main.activity_non_members_reigster.*

class NonMemberRegister : androidx.fragment.app.Fragment() {

    //    한글 2~4자 (공백 허용 X) or 영문 First name 2~10, Last name 2~10
    private val nameRegex = Regex("^[가-힣]{2,4}|[a-zA-Z]{2,10}\\s[a-zA-Z]{2,10}$")

    //    3자리 - 3 or 4자리 - 4자리
//    첫자리는 반드시 0으로 시작.
    private val phoneRegex = Regex("^[0](\\d{2})(\\d{3,4})(\\d{4})")

    //   첫자리는 반드시 0이 아닌 숫자 총 2자리까지 입력가능 (1~99 입력 가능)
    private val peopleNumberRegex = Regex("^[1-9](\\d?)")


    fun verifyName(input_name: String): Boolean = input_name.matches(nameRegex)
    fun verifyPhoneNumber(input_phone_number: String): Boolean =
        input_phone_number.matches(phoneRegex)

    fun verifyPeopleNumber(input_people_number: String): Boolean =
        input_people_number.matches(peopleNumberRegex)

    // non_member 페이지를 열어줌
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.activity_non_members_reigster, container, false)
    }


    override fun onStop() {
        inputLayoutInitialize()
        super.onStop()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        user_name_input_editText.addTextChangedListener(object : TextWatcher {
            //            text에 변화가 있을 때마다
            override fun afterTextChanged(s: Editable?) {
                if (!verifyName(s.toString())) {
                    user_name_input_layout.error = "2~4자 한글 또는 영문이름을 입력해주세요"
                    register_customer_button.isEnabled = false
                } else {
                    user_name_input_layout.error = null
                    user_name_input_layout.hint = null
                }

                //    둘다 에러가 없을 때 등록 버튼 활성화
                register_customer_button.isEnabled =
                    (user_name_input_layout.error == null
                            && user_phone_number_layout.error == null
                            && people_number_layout.error == null
                            && !user_phone_number_editText.text.isNullOrBlank()
                            && !people_number_editText.text.isNullOrBlank())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        user_phone_number_editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (!verifyPhoneNumber(s.toString())) {
                    user_phone_number_layout.error = "10~11자리 전화번호를 입력해주세요"
                    register_customer_button.isEnabled = false
                } else {
                    user_phone_number_layout.error = null
                    user_phone_number_layout.hint = null
                }
                register_customer_button.isEnabled =
                    (user_name_input_layout.error == null
                            && user_phone_number_layout.error == null
                            && people_number_layout.error == null
                            && !user_name_input_editText.text.isNullOrBlank()
                            && !people_number_editText.text.isNullOrBlank())
            }

            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        people_number_editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (!verifyPeopleNumber(s.toString())) {
                    people_number_layout.error = "단체 손님은 가게에 문의해주세요"
                    register_customer_button.isEnabled = false
                } else {
                    people_number_layout.error = null
                    people_number_layout.hint = null
                }
                register_customer_button.isEnabled =
                    (user_name_input_layout.error == null
                            && user_phone_number_layout.error == null
                            && people_number_layout.error == null
                            && !user_name_input_editText.text.isNullOrBlank()
                            && !user_phone_number_editText.text.isNullOrBlank())
            }

            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })


        register_customer_button.setOnClickListener {
//            둘다 입력되어있으면 code flow는 첫줄에서 반환됨.

            NonMemberRegisterAsyncTask(this@NonMemberRegister)
                .execute(
                    user_name_input_editText.text.toString()
                    , user_phone_number_editText.text.toString()
                    , people_number_editText.text.toString()
                )
        }


        super.onActivityCreated(savedInstanceState)
    }

    private fun inputLayoutInitialize() {
        user_name_input_editText.text?.clear()
        user_name_input_editText.clearFocus()
        user_name_input_layout.error = null
        user_name_input_layout.hint = "이름을 입력해주세요"

        user_phone_number_editText.text?.clear()
        user_phone_number_editText.clearFocus()
        user_phone_number_layout.error = null
        user_phone_number_layout.hint = "전화번호를 입력해주세요"

        people_number_editText.text?.clear()
        people_number_editText.clearFocus()
        people_number_layout.error = null
        people_number_layout.hint = "총 몇 분이 오셨나요?"
    }

}

