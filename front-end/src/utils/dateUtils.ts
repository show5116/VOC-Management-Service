import { add } from 'date-fns'

export const daysFromToday = (days: number): Date => {
  return add(new Date(), { days: days })
}
