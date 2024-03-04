import { add, format, startOfMonth } from 'date-fns'

export const vocBaseFormat = (date: Date): string => {
  return format(date, 'yyyyMMddHHmmss')
}

export const daysFromToday = (days: number): Date => {
  return add(new Date(), { days: days })
}

export const firstDayThisMonth = (): Date => {
  return startOfMonth(new Date())
}
