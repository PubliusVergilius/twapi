package main

import "fmt"

func main () {
	a := new(A)
	a.Name = "a"

	b := new(B)
	b.Name = "b"

	a.Next = b

	fmt.Println(a.toString())
}

type A struct {
  Name string
  Next *B
}

func (a *A) toString() string{
	return "Name A: " + a.Name + "; Next: " + a.Next.toString()
}

type B struct {
 Name string
 Previous *A
}

func (b *B) toString() string{
  return "Name B: " + b.Name
}


