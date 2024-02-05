import i18n from 'i18next'
import { initReactI18next } from 'react-i18next'
import { ko } from './langs/ko'
import { en } from './langs/en'
import { cn } from './langs/cn'

const resources = {
  en: {
    translation: en,
  },
  ko: {
    translation: ko,
  },
  cn: {
    translation: cn,
  },
}

/*
 * login한 상태값으로 설정 해야함
 */
i18n.use(initReactI18next).init({
  resources,
  lng: 'en', // 기본 설정 언어, 'cimode'로 설정할 경우 키 값으로 출력된다.
  fallbackLng: 'en', // 번역 파일에서 찾을 수 없는 경우 기본 언어
  interpolation: {
    escapeValue: true,
  },
})

export default i18n
