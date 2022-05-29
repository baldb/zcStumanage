export const setStorage = (key, data) => {
  localStorage.setItem(key, JSON.stringify(data))
}
export const getStorage = (key) => {
  const data = localStorage.getItem(key)
  if (!data) return
  return JSON.parse(data)
}

export const removeStorage = (key) => {
  const data = getStorage(key)
  if (!data) return
  localStorage.removeItem(key)
  return true
}
