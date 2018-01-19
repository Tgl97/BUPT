ifneq ($(KERNELRELEASE),)
obj-m := pf.o
else
	PWD := $(shell pwd)
	KVER := $(shell uname -r)
	KDIR := /lib/modules/$(KVER)/build
all:
	$(MAKE) -C $(KDIR) M=$(PWD) modules
clean:
	rm -rf *.ko *.o *.mod.o *.mod.c *.symvers
endif
